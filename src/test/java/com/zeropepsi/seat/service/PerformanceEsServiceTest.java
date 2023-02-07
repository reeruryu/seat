package com.zeropepsi.seat.service;

import com.zeropepsi.seat.domain.document.FacilityDoc;
import com.zeropepsi.seat.domain.document.PerformanceDoc;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@SpringBootTest
class PerformanceEsServiceTest {

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	private PerformanceEsService performanceEsService;


	@Test
	void pageTest() {
		Long after = 11L;
		List<PerformanceDoc> list = performanceEsService.searchPerformance(" ", after, 10);

		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId() + ": " + list.get(i).getName());
		}

	}
}