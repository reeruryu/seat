package com.zeropepsi.seat.client.kopis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "dbs")
public class PerformanceFacilityDetailResponse {
	private PerformanceFacilityDetail performanceFacilityDetail;

	@Getter
	@Setter
	@XmlRootElement(name = "db")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class PerformanceFacilityDetail {

		@XmlElement(name = "fcltynm")
		private String fcltynm; // 공연시설명

		@XmlElement(name = "mt10id")
		private String mt10id; // 공연시설ID

		@XmlElement(name = "mt13cnt")
		private String mt13cnt; // 공연장 수

		@XmlElement(name = "fcltychartr")
		private String fcltychartr; //시설특성

		@XmlElement(name = "opende")
		private String opende; // 개관연도

		@XmlElement(name = "seatscale")
		private String seatscale; // 객석 수

		@XmlElement(name = "telno")
		private String telno; // 전화번호

		@XmlElement(name = "relateurl")
		private String relateurl; // 홈페이지

		@XmlElement(name = "adres")
		private String adres; // 주소

		@XmlElement(name = "la")
		private String la; // 위도

		@XmlElement(name = "lo")
		private String lo; // 경도

	}

}
