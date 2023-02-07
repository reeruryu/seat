package com.zeropepsi.seat.controller;

import com.zeropepsi.seat.domain.document.PerformanceDoc;
import com.zeropepsi.seat.service.PerformanceEsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PerformanceEsController {

	private final PerformanceEsService performanceEsService;

	@GetMapping("/search/performance")
	public ResponseEntity<List<PerformanceDoc>> searchPerformance(@RequestParam String name,
		@RequestParam(required = false) Long after, @RequestParam int size) {
		List<PerformanceDoc> results = performanceEsService.searchPerformance(name, after, size);
		return ResponseEntity.ok(results);
	}

	@PostMapping("/save/performance")
	public ResponseEntity<Void> savePerformanceDoc(){
		performanceEsService.savePerformances();
		return ResponseEntity.ok().build();
	}

}
