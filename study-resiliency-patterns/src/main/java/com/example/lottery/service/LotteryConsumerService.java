package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;

@Service
public class LotteryConsumerService {
	private static final String LOTTERY_API_URL = "http://localhost:7100/lottery/api/v1/numbers?column=3";

	// @Retry(name = "lottery", fallbackMethod = "getLotteryNumbersFallback")
	// @RateLimiter(name = "lottery", fallbackMethod = "getLotteryNumbersFallback")
	//@CircuitBreaker(name = "lottery", fallbackMethod = "getLotteryNumbersFallback")
	@Bulkhead(name = "lottery",type = Type.SEMAPHORE, fallbackMethod = "getLotteryNumbersFallback")
	public String getLotteryNumbers() {
		var restTemplate = new RestTemplate();
		return restTemplate.getForEntity(LOTTERY_API_URL, String.class).getBody();
	}

	//@TimeLimiter(name = "lottery", fallbackMethod = "getAsyncLotteryNumbersFallback")
	public CompletableFuture<String> getAsyncLotteryNumbers() {
		return CompletableFuture.supplyAsync(() -> {
			var restTemplate = new RestTemplate();
			return restTemplate.getForEntity(LOTTERY_API_URL, String.class).getBody();
		});
	}

	public String getLotteryNumbersFallback(Throwable t) {
		System.err.println("Fallback method is running..." + t.getMessage());
		return List.of(4, 8, 15, 16, 23, 42).toString();
	}

	public CompletableFuture<String> getAsyncLotteryNumbersFallback(Throwable t) {
		return CompletableFuture.supplyAsync(() -> {
			System.err.println("Async Fallback method is running..." + t.getMessage());
			return List.of(4, 8, 15, 16, 23, 42).toString();
		});
	}
}
