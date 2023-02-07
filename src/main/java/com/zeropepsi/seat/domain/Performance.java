package com.zeropepsi.seat.domain;

import com.zeropepsi.seat.client.kopis.PerformanceResponse.Prf;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Performance {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String mt20id; // 공연ID

	private String prfnm; // 공연명

	private LocalDate prfpdfrom; // 공연시작일

	private LocalDate prfpdto; // 공연종료일

	private String poster; // 포스터이미지경로

	private String genrenm; // 공연 장르명

	private String prfstate; // 공연상태

	private String openrun; // 오픈런

	@ManyToOne
	private Theater theater;

	public static Performance of(Prf prf, Theater theater, LocalDate from, LocalDate to) {
		return Performance.builder()
			.mt20id(prf.getMt20id())
			.theater(theater)
			.prfnm(prf.getPrfnm())
			.prfpdfrom(from)
			.prfpdto(to)
			.poster(prf.getPoster())
			.genrenm(prf.getGenrenm())
			.openrun(prf.getOpenrun())
			.build();
	}

}
