package com.zeropepsi.seat.repository.es;

import com.zeropepsi.seat.domain.document.FacilityDoc;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityEsRespository extends ElasticsearchRepository<FacilityDoc, Long> {
	List<FacilityDoc> findAllByNameContains(String name); // 이름 포함 시

	List<FacilityDoc> findAllBySido(String sido); // 시도만 입력 시

	List<FacilityDoc> findAllBySidoAndGugun(String sido, String gugun); // 둘다 입력 시

}

