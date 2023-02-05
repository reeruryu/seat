package com.zeropepsi.seat.service;

import com.zeropepsi.seat.client.KopisClient;
import com.zeropepsi.seat.client.kopis.PerformanceResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KopisService {

	@Value("${kopis.key}")
	private String kopisKey;

	private final KopisClient kopisClient;

	public PerformanceResponse getPerformanceList(Integer cpage, Integer rows, String prfstate) {
		LocalDate nowDt = LocalDate.now();
		String nowDtStr = nowDt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String futureDtStr = nowDt.plusMonths(6).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		return kopisClient.getPerformanceList(kopisKey,
			nowDtStr, futureDtStr, cpage, rows, prfstate);
	}


}
