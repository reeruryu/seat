package com.zeropepsi.seat.service;

import com.zeropepsi.seat.domain.Facility;
import com.zeropepsi.seat.domain.document.FacilityDoc;
import com.zeropepsi.seat.repository.FacilityRepository;
import com.zeropepsi.seat.repository.es.FacilityEsRespository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacilityEsService {

	private final ElasticsearchOperations elasticsearchOperations;
	private final FacilityRepository facilityRepository;
	private final FacilityEsRespository facilityEsRepository;

	/**
	 * 시설 검색
	 */
	public List<FacilityDoc> searchFacility(String name, Long after, int size) {

		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		if (after != null) {
			queryBuilder.withSearchAfter(Arrays.asList(after));
		}

		NativeSearchQuery searchQuery = queryBuilder
			.withQuery(QueryBuilders.queryStringQuery("*" + name + "*").field("name"))
			.withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC))
			.withPageable(PageRequest.of(0, size))
			.build();

		SearchHits<FacilityDoc> searchHits = elasticsearchOperations.search(searchQuery, FacilityDoc.class);
		List<FacilityDoc> results = searchHits.stream()
			.map(SearchHit::getContent)
			.collect(Collectors.toList());

		return results;
	}

	/**
	 * mysql에 저장된 facility es에 저장
	 */
	public void saveFacilities() {
		List<Facility> facilityList = facilityRepository.findAll();

		facilityEsRepository.saveAll(facilityList.stream()
			.map(FacilityDoc::from)
			.collect(Collectors.toList()));
	}

}
