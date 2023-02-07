package com.zeropepsi.seat.repository.es;

import com.zeropepsi.seat.domain.document.PerformanceDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceEsRepository extends ElasticsearchRepository<PerformanceDoc, Long> {

}

