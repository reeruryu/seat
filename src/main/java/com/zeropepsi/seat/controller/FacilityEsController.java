package com.zeropepsi.seat.controller;

import com.zeropepsi.seat.domain.document.FacilityDoc;
import com.zeropepsi.seat.service.FacilityEsService;
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
public class FacilityEsController {

	private final FacilityEsService searchService;

	@GetMapping("/search/facility")
	public ResponseEntity<List<FacilityDoc>> searchFacility(@RequestParam String name,
		@RequestParam(required = false) Long after, @RequestParam int size) {
		List<FacilityDoc> results = searchService.searchFacility(name, after, size);
		return ResponseEntity.ok(results);
	}

	@PostMapping("/save/facility")
	public ResponseEntity<Void> saveFacilityDoc(){
		searchService.saveFacilities();
		return ResponseEntity.ok().build();
	}

}
