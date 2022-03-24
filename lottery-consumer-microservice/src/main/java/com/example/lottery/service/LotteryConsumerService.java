package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnProperty(name="loadBalancing",havingValue = "simple")
public class LotteryConsumerService {
	private static final String LOTTERY_API_URL = 
			"http://%s:%d/lottery/api/v1/numbers?column=3";
	private final DiscoveryClient discoveryClient;
	private List<ServiceInstance> instances;
	private AtomicInteger counter = new AtomicInteger();
	
	public LotteryConsumerService(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@PostConstruct
	public void init() {
		instances = discoveryClient.getInstances("lottery");
	}
	
	@Scheduled(fixedRate= 5_000)
	public void consumeLotteryService() {
		var index = counter.getAndIncrement() % instances.size();
		var instance = instances.get(index);
		var restTemplate = new RestTemplate();
		String url = LOTTERY_API_URL.formatted(instance.getHost(),instance.getPort());
		var response = restTemplate.getForEntity(url, String.class);
		System.err.println(response.getBody());
	}
}
