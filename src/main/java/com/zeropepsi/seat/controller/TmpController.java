package com.zeropepsi.seat.controller;

import com.zeropepsi.seat.PerformanceDto;
import com.zeropepsi.seat.client.kopis.PerformanceResponse;
import com.zeropepsi.seat.service.KopisService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TmpController {

	private final KopisService service;

	@GetMapping("/performanceTest") //
	public ResponseEntity<List<PerformanceDto>> pageTest(String stDtate, String edDate, Integer cpage, Integer rows) {
		List<PerformanceDto> list = new ArrayList<>();

		List<PerformanceResponse.Performance> p = service.
			getPerformanceList(stDtate, edDate, cpage, rows).getPerformanceList();

		for (PerformanceResponse.Performance pe: p) {
			list.add(PerformanceDto.builder().id(pe.getMt20id()).stDate(pe.getPrfpdfrom()).edDate(pe.getPrfpdto()).build());
		}
		return ResponseEntity.ok(list);
	}
}
