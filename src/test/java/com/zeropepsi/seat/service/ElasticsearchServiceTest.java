package com.zeropepsi.seat.service;

import com.zeropepsi.seat.domain.document.FacilityDoc;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@SpringBootTest
class FacilityEsServiceTest {

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	private FacilityEsService facilityEsService;


	@Test
	void pageTest() {
		Long after = null;
		List<FacilityDoc> list = facilityEsService.searchFacility("광주", after, 10);

		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId() + ": " + list.get(i).getName());
		}

	}
}