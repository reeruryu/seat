package com.zeropepsi.seat.client;

import com.zeropepsi.seat.client.kopis.PerformanceDetailResponse;
import com.zeropepsi.seat.client.kopis.PerformanceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kopis", url = "${kopis.api.url}")
public interface KopisClient {

	// 공연 목록
	@GetMapping("/pblprfr")
	PerformanceResponse getPrfList(
		@RequestParam("service") String service,
		@RequestParam("stDate")  String stDate,
		@RequestParam("edDate") String edDate,
		@RequestParam("cpage") Integer cpage,
		@RequestParam("rows") Integer rows,
		@RequestParam("prfstate") String prfstate);

	// 공연 상세 목록 -> 공연시설명(공연장명)을 얻어 오기 위함
	@GetMapping("/pblprfr/{mt20id}")
	PerformanceDetailResponse getPrfDetail(
		@PathVariable("mt20id") String mt20id,
		@RequestParam("service") String service);

}
