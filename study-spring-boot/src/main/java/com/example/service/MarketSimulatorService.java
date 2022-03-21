package com.example.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.entity.Stock;

@Service
@Scope("singleton") // default scope
public class MarketSimulatorService {
	// Dependency Injection (DI) Framework -> Spring Framework
	private final ApplicationEventPublisher eventPublisher;
	
	// DI -> Constructor Injection
	public MarketSimulatorService(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Scheduled(fixedRate = 3_000)
	public void createRandomMarketData() {
		var randomPrice = ThreadLocalRandom.current().nextDouble(100, 120);
		var stock = new Stock("orcl", randomPrice);
		eventPublisher.publishEvent(stock);
	}
}
