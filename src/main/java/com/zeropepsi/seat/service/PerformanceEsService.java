package com.zeropepsi.seat.service;

import com.zeropepsi.seat.domain.Performance;
import com.zeropepsi.seat.domain.document.PerformanceDoc;
import com.zeropepsi.seat.repository.PerformanceRepository;
import com.zeropepsi.seat.repository.es.PerformanceEsRepository;
import java.util.Arrays;
import java.util.Date;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PerformanceEsService {

	private final ElasticsearchOperations elasticsearchOperations;
	private final PerformanceRepository performanceRepository;
	private final PerformanceEsRepository performanceEsRepository;

	/**
	 * 공연 검색
	 */
	public List<PerformanceDoc> searchPerformance(String name, Long after, int size) {

		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		if (after != null) {
			queryBuilder.withSearchAfter(Arrays.asList(after));
		}

		NativeSearchQuery searchQuery = queryBuilder
			.withQuery(QueryBuilders.queryStringQuery("*" + name + "*").field("name"))
			.withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC))
			.withPageable(PageRequest.of(0, size))
			.build();

		SearchHits<PerformanceDoc> searchHits = elasticsearchOperations.search(searchQuery, PerformanceDoc.class);
		List<PerformanceDoc> results = searchHits.stream()
			.map(SearchHit::getContent)
			.collect(Collectors.toList());

		return results;
	}

	/**
	 * 기간 지난 데이터 체크 후 삭제 - 스케줄러
	 */
	@Transactional
	public void deletePerformanceComplete() {
		NativeSearchQuery query = new NativeSearchQueryBuilder()
			.withQuery(QueryBuilders.boolQuery()
				.must(QueryBuilders.rangeQuery("endDate").lt(new Date())))
			.build();

		elasticsearchOperations.delete(query, PerformanceDoc.class);
	}

	/**
	 * mysql에 저장된 performance es에 저장 (초기 세팅)
	 */
	public void savePerformances() {
		List<Performance> performanceList = performanceRepository.findAll();

		performanceEsRepository.saveAll(performanceList.stream()
			.map(PerformanceDoc::from)
			.collect(Collectors.toList()));
	}

}
