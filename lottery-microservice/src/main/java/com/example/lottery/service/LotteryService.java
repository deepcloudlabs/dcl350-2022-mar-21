package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LotteryService {
	private final int lotteryMax;
	private final int lotterySize;
	
	public LotteryService(
			@Value("${lotteryMax}") int lotteryMax, 
			@Value("${lotterySize}") int lotterySize) {
		this.lotteryMax = lotteryMax;
		this.lotterySize = lotterySize;
	}

	public List<List<Integer>> draw(int column) {
		return IntStream.range(0, column)
				        .mapToObj(i -> draw())
				        .toList();
	}

	private List<Integer> draw() {
		return ThreadLocalRandom.current().ints(1, lotteryMax)
				    .distinct()
				    .limit(lotterySize)
				    .sorted()
				    .boxed()
				    .toList();
	}

}
