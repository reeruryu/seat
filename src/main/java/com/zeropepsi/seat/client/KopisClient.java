package com.zeropepsi.seat.client;

import com.zeropepsi.seat.client.kopis.PerformanceFacilityDetailResponse;
import com.zeropepsi.seat.client.kopis.PerformanceFacilityResponse;
import com.zeropepsi.seat.client.kopis.PerformanceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kopis", url = "${kopis.api.url}")
public interface KopisClient {

	// 공연 목록
	@GetMapping("/pblprfr")
	PerformanceResponse getPerformanceList(
		@RequestParam String service,
		@RequestParam String stDate,
		@RequestParam String edDate,
		@RequestParam Integer cpage,
		@RequestParam Integer rows);

	// 공연 시설 목록
	@GetMapping("/prfplc")
	PerformanceFacilityResponse getPerformanceFacilityList(
		@RequestParam String service,
		@RequestParam Integer cpage,
		@RequestParam Integer rows
	);

	// 공연 시설 안내 : prfplc/{공연시설아이디}
	@GetMapping("/prfplc/{mt10id}")
	PerformanceFacilityDetailResponse getPerformanceFacilityDetail(
		@RequestParam String service,
		@PathVariable String mt10id
	);

}
