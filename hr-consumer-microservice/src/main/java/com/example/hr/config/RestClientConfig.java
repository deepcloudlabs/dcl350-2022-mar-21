package com.example.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("deprecation")
@Configuration
@EnableScheduling
public class RestClientConfig {

	@Bean
	public RestTemplate createRestTemplate() {
		var restTemplate = new RestTemplate();

		return restTemplate;
	}
	
	@Bean
	public AsyncRestTemplate createAsyncRestTemplate() {
		var restTemplate = new AsyncRestTemplate();
		
		return restTemplate;
	}
}
