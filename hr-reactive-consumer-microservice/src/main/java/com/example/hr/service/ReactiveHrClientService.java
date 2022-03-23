package com.example.hr.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ReactiveHrClientService {
	private final WebClient webClient = 
			WebClient.builder().baseUrl("http://localhost:8100/hr/api/v1").build();
	
	@Scheduled(fixedRate = 3_000)
	public void callRestApi() {
		List.of("IT", "SALES", "FINANCE", "HR")
		    .forEach(department ->
				webClient.put()
				         .uri(uriBuilder -> uriBuilder.path("/employees")
				        		                      .queryParam("department", department)
				        		                      .queryParam("rate", 0.01)
				        		                      .build())
				         .retrieve()
				         .bodyToMono(String.class)
				         .subscribe(System.err::println)
		);
	}
}
