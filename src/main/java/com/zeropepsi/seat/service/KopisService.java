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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KopisService {

	@Value("${kopis.key}")
	private String kopisKey;
	private final KopisClient kopisClient;

	private final TheaterRepository theaterRepository;
	private final PerformanceRepository performanceRepository;

	/**
	 * // update, delete 로직 배치로 구현 예정
	 * 매달 1일 새벽 2시 마다 api 가져오기
	 * 	(이미 6개월 치 데이터 저장 중 상태)
	 */
	@Transactional
	@Scheduled(cron = "0 0 2 1 * *")
	public Integer updatePerformance() {
		List<Prf> prfList = getPrfList();

		int cnt = 0;
		List<Performance> performanceList = new ArrayList<>();
		for (Prf prf: prfList) {

			boolean isExistMt20id = performanceRepository.existsByMt20id(prf.getMt20id());
			if (isExistMt20id) {
				continue;
			}

			String theaterName = "";
			try {
				theaterName = getTheaterName(prf.getMt20id());
			} catch (Exception e) {
				continue;
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

			performanceList.add(Performance.of(
				prf, theater, prf.getPrfpdfromDate(), prf.getPrfpdtoDate()));
		}

		performanceRepository.saveAll(performanceList);
		System.out.println(cnt + "개의 db가 없습니다.");

		return performanceList.size();
	}

	// scheduler : 1일 or 1달 마다 불러오기
	// prfstate : 01 공연 예정, 02 공연 중, 03 공연 완료
	public List<Prf> getPrfList() {
		LocalDate startDt = LocalDate.now().plusMonths(5);
		String startDtStr = startDt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String edDtStr = startDt.plusMonths(1).minusDays(1)
			.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		return kopisClient.getPrfList(kopisKey,
			startDtStr, edDtStr, 1, 500, "01").getPrfList();
	}

	/**
	 * 매일 새벽 1시 마다 기간 지난 거 체크 후 삭제
	 */
	@Transactional
	@Scheduled(cron = "0 0 1 * * *")
	public void deletePerformanceComplete() {
		performanceRepository.deleteOutsidePeriod(LocalDate.now());
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
