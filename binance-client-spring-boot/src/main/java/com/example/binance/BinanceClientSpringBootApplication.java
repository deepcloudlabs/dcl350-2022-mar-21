package com.example.binance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BinanceClientSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinanceClientSpringBootApplication.class, args);
	}

}
