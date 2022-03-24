package com.example.lottery.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {
	@Autowired
	private LotteryConsumerService lotteryConsumerService;
	
	@PostConstruct
	public void init() {
	   	System.err.println(lotteryConsumerService.getClass().getSimpleName());
	}
	
	// @Scheduled(fixedRate= 1)
	public void callService() {
		System.err.println(lotteryConsumerService.getLotteryNumbers());
	}
	
	@Scheduled(fixedRate= 3_000)
	public void callAsyncService() {
		lotteryConsumerService.getAsyncLotteryNumbers().thenAccept(System.err::println);
	}

}
