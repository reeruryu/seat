package com.zeropepsi.seat.repository.es;

import com.zeropepsi.seat.domain.document.FacilityDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityEsRespository extends ElasticsearchRepository<FacilityDoc, Long> {

}

