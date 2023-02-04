package com.zeropepsi.seat.client.kopis;

import com.zeropepsi.seat.client.kopis.PerformanceResponse.Performance;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "dbs")
public class PerformanceFacilityResponse {

	private List<PerformanceFacility> performanceFacilityList;

	@XmlElement(name="db")
	public List<PerformanceFacility> getPerformanceFacilityList() {
		return performanceFacilityList;
	}

	public void setPerformanceFacilityList(List<PerformanceFacility> performanceFacilityList) {
		this.performanceFacilityList = performanceFacilityList;
	}

	@Getter
	@Setter
	@XmlRootElement(name = "db")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class PerformanceFacility {

		@XmlElement(name = "fcltynm")
		private String fcltynm; // 공연시설명

		@XmlElement(name = "mt10id")
		private String mt10id; // 공연시설ID

		@XmlElement(name = "mt13cnt")
		private String mt13cnt; // 공연장 수

		@XmlElement(name = "fcltychartr")
		private String fcltychartr; // 시설특성

		@XmlElement(name = "sidonm")
		private String sidonm; // 지역(시도)

		@XmlElement(name = "gugunnm")
		private String gugunnm; // 지역(구군)

		@XmlElement(name = "opende")
		private String opende; // 개관연도

	}

}
