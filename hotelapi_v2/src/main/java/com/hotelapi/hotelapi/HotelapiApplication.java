package com.hotelapi.hotelapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HotelapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelapiApplication.class, args);
	}

}
