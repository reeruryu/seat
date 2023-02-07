package com.zeropepsi.seat.service;

import com.zeropepsi.seat.client.KopisClient;
import com.zeropepsi.seat.client.kopis.PerformanceDetailResponse;
import com.zeropepsi.seat.client.kopis.PerformanceResponse.Prf;
import com.zeropepsi.seat.domain.Performance;
import com.zeropepsi.seat.domain.Theater;
import com.zeropepsi.seat.repository.PerformanceRepository;
import com.zeropepsi.seat.repository.TheaterRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KopisService {

	@Value("${kopis.key}")
	private String kopisKey;
	private final KopisClient kopisClient;

	private final TheaterRepository theaterRepository;
	private final PerformanceRepository performanceRepository;

	public Integer updatePerformance() {
		List<Prf> prfList = getPrfList(100, "02");

		int cnt = 0;
		List<Performance> performanceList = new ArrayList<>();
		for (Prf prf: prfList) {

			String theaterName = "";
			try {
				theaterName = getTheaterName(prf.getMt20id());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			if (theaterName == "") {
				cnt++;
				continue;
			}

			Theater theater = theaterRepository
				.findByNameAndFacility_Name(theaterName, prf.getFcltynm());
			if (theater == null) {
				cnt++;
				System.out.println(prf.getFcltynm() + "은 db에 없습니다.");
				continue;
			}

			LocalDate from = getLocalDate(prf.getPrfpdfrom());
			LocalDate to = getLocalDate(prf.getPrfpdto());

			performanceList.add(Performance.of(prf, theater, from, to));
		}

		performanceRepository.saveAll(performanceList);
		System.out.println(cnt + "개의 db가 없습니다.");

		return performanceList.size();
	}

	// scheduler : 1일 or 1달 마다 불러오기
	// prfstate : 01 공연 예정, 02 공연 중, 03 공연 완료
	public List<Prf> getPrfList(Integer rows, String prfstate) {
		LocalDate nowDt = LocalDate.now();
		String nowDtStr = nowDt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String futureDtStr = nowDt.plusMonths(4).minusDays(1)
			.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		return kopisClient.getPrfList(kopisKey,
			nowDtStr, futureDtStr, 1, rows, prfstate).getPrfList();
	}

	private static LocalDate getLocalDate(String dateStr) {
		return LocalDate.of(Integer.parseInt(dateStr.substring(0, 4)),
			Integer.parseInt(dateStr.substring(5, 7)),
			Integer.parseInt(dateStr.substring(8))
		);
	}

	public String getTheaterName(String mt20id) throws Exception {
		PerformanceDetailResponse response = kopisClient.getPrfDetail(mt20id, kopisKey);
		String facilityOrTheaterName = response.getPrfDetail().getFcltynm();

		String[] strings = facilityOrTheaterName.split("\\)", -1);

		String result = "";
		if (strings.length > 3) {
			result = strings[1].trim().substring(1) + ")";
		} else if (strings.length > 2) {
			if (Objects.equals(strings[1], "") && Objects.equals(strings[2], "")) {
				result = strings[0].split("\\(", 2)[1] + ")";
			} else {
				result = strings[1].substring(2);
			}
		} else if (strings.length > 1) {
			result = facilityOrTheaterName.split("\\(", -1)[1].substring(0, facilityOrTheaterName.split("\\(")[1].length() - 1);
		}

		return result;
	}

}
