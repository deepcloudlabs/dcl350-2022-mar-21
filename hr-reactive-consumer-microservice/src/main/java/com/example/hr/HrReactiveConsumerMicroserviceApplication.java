package com.example.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HrReactiveConsumerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrReactiveConsumerMicroserviceApplication.class, args);
	}

}
