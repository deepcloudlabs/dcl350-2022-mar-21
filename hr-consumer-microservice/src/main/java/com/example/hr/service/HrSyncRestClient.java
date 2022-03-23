package com.example.hr.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnProperty(name = "client", havingValue = "sync")
public class HrSyncRestClient {
	private final String hrRestApiBaseUrl;
	private final RestTemplate restTemplate;
	
	
	public HrSyncRestClient(
			@Value("${hrRestApiBaseUrl}") String hrRestApiBaseUrl, 
			RestTemplate restTemplate) {
		this.hrRestApiBaseUrl = hrRestApiBaseUrl;
		this.restTemplate = restTemplate;
	}


	@Scheduled(fixedRate = 3_000)
	public void callRestApi() {
		restTemplate.put(
			"%s/employees?department=%s&rate=%f".formatted(hrRestApiBaseUrl,"SALES",0.1), 
			null);
	}
}
