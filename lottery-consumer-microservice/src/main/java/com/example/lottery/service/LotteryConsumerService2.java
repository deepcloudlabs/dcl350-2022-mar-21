package com.example.lottery.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name="loadBalancing",havingValue = "feign")
public class LotteryConsumerService2 {
	private final LotteryService lotteryService;
	
	public LotteryConsumerService2(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@Scheduled(fixedRate= 5_000)
	public void consumeLotteryService() {
		System.err.println(lotteryService.getLotteryNumbers(10));
	}
}
