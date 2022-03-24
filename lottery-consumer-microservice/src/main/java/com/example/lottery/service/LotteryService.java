package com.example.lottery.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "lottery")
public interface LotteryService {
	@GetMapping("/lottery/api/v1/numbers")
	List<List<Integer>> getLotteryNumbers(@RequestParam int column);
}
