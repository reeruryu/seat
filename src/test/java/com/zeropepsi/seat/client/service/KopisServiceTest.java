package com.zeropepsi.seat.client.service;

import static org.junit.jupiter.api.Assertions.*;

import com.zeropepsi.seat.client.kopis.PerformanceFacilityDetailResponse;
import com.zeropepsi.seat.client.kopis.PerformanceFacilityResponse;
import com.zeropepsi.seat.client.kopis.PerformanceResponse;
import com.zeropepsi.seat.service.KopisService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KopisServiceTest {

	@Autowired
	private KopisService kopisService;

	@Test
	void tmp() {

		// stDate, String edDate, Integer cpage, Integer row
		List<PerformanceResponse.Performance> list =
			kopisService.getPerformanceList("20000922", "20160101", 1, 10).getPerformanceList();

		for (int i = 0; i < list.size(); i++) {
			PerformanceResponse.Performance p = list.get(i);
			System.out.println("공연id:" + p.getMt20id());
//			System.out.println("공연명:" + p.getPrfnm());
			System.out.println("공연시작일:" + p.getPrfpdfrom());
			System.out.println("공연종료일:" + p.getPrfpdto());
//			System.out.println("공연시설명(공연장명):" + p.getFcltynm());
//			System.out.println("포스터이미지경로:" + p.getPoster());
//			System.out.println("공연장르명:" + p.getGenrenm());
//			System.out.println("공연상태:" + p.getPrfstate());
//			System.out.println("오픈런:" + p.getOpenrun());
			System.out.println();
		}
		System.out.println(list.size());

//		FeignException

		assertEquals(1, 1);

	}

	@Test
	void tmp2() {

		List<PerformanceFacilityResponse.PerformanceFacility> list = kopisService.getPerformanceFacilityList(2, 1000)
			.getPerformanceFacilityList();

		for (int i = 0; i < list.size(); i++) {
			PerformanceFacilityResponse.PerformanceFacility p = list.get(i);
			System.out.println("공연시설명:" + p.getFcltynm());
//			System.out.println("공연시설ID:" + p.getMt10id());
		}
		System.out.println(list.size());

	}

//	@Test
//	void tmp3() {
//
//		PerformanceFacilityDetailResponse.PerformanceFacilityDetail p =
//			kopisService.tmp3("FC000517").getPerformanceFacilityDetail();
//
//		System.out.println("공연시설명:" + p.getFcltynm());
//
//	}
}