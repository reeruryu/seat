package com.zeropepsi.seat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeatApplication.class, args);
	}

}
