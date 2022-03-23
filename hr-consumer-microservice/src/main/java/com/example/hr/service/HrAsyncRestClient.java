package com.example.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;

@Service
@ConditionalOnProperty(name = "client", havingValue = "async")
public class HrAsyncRestClient {
	private final String hrRestApiBaseUrl;
	private final AsyncRestTemplate restTemplate;
	
	
	public HrAsyncRestClient(
			@Value("${hrRestApiBaseUrl}") String hrRestApiBaseUrl, 
			AsyncRestTemplate restTemplate) {
		this.hrRestApiBaseUrl = hrRestApiBaseUrl;
		this.restTemplate = restTemplate;
	}


	@Scheduled(fixedRate = 3_000)
	public void callRestApi() {
		for (var department : List.of("SALES", "IT", "FINANCE"))
		restTemplate.put(
			"%s/employees?department=%s&rate=%f".formatted(hrRestApiBaseUrl,department,0.01), 
			null).addCallback(
					(response) -> {
					    System.err.println(response.toString());	
					}, 
					(error) -> {
						System.err.println(error.getMessage());	
					});
		
	}
}
