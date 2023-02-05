package com.zeropepsi.seat.client.kopis;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "dbs")
public class PerformanceDetailResponse {

	private PerformanceDetail performanceDetail;

	@XmlElement(name="db")
	public PerformanceDetail getPerformanceDetail() {
		return performanceDetail;
	}

	public void setPerformanceDetail(PerformanceDetail performanceDetail) {
		this.performanceDetail = performanceDetail;
	}

	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@XmlRootElement(name = "db")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class PerformanceDetail {

		@XmlElement(name = "mt20id")
		private String mt20id; // 공연ID

		@XmlElement(name = "fcltynm")
		private String fcltynm; // 공연시설명(공연장명)

	}

}