package com.zeropepsi.seat.domain.document;

import com.zeropepsi.seat.domain.Facility;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "facility")
public class FacilityDoc {

	@Id
	@Field(type = FieldType.Keyword)
	private Long id;

	@Field(type = FieldType.Text)
	private String name;

	@Field(type = FieldType.Integer)
	private Integer totalSeatCnt;

	@Field(type = FieldType.Keyword)
	private String sido;

	@Field(type = FieldType.Keyword)
	private String gugun;

	@Field(type = FieldType.Text)
	private String address;

	public static FacilityDoc from(Facility f) {
		return FacilityDoc.builder()
			.id(f.getId())
			.name(f.getName())
			.totalSeatCnt(f.getTotalSeatCnt())
			.sido(f.getSido())
			.gugun(f.getGugun())
			.address(f.getAddress())
			.build();
	}

}