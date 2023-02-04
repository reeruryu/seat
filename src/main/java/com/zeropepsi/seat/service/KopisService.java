package com.zeropepsi.seat.service;

import com.zeropepsi.seat.client.KopisClient;
import com.zeropepsi.seat.client.kopis.PerformanceFacilityDetailResponse;
import com.zeropepsi.seat.client.kopis.PerformanceFacilityResponse;
import com.zeropepsi.seat.client.kopis.PerformanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KopisService {

	@Value("${kopis.key}")
	private String kopisKey;

	private final KopisClient kopisClient;

	public PerformanceResponse getPerformanceList( // 공연은 open api로 .. 캐싱하자
		String stDate, String edDate, Integer cpage, Integer rows) {
		return kopisClient.getPerformanceList(kopisKey, stDate, edDate, cpage,rows);
	}

	// 공연장은 한 달에 한 번 open api를 호출하여 스프링 배치를 통해 미리 저장
	public PerformanceFacilityResponse getPerformanceFacilityList(Integer cpage, Integer rows) {
		return kopisClient.getPerformanceFacilityList(kopisKey, cpage, rows);
	}

	public PerformanceFacilityDetailResponse getPerformanceFacilityDetail(String mt10id) {
		return kopisClient.getPerformanceFacilityDetail(kopisKey, mt10id);
	}

}
