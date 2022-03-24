package com.example.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.service.LotteryService;

@RestController
@RequestScope
@RequestMapping("numbers")
@Validated
@CrossOrigin
public class LotteryController {

	private final LotteryService lotteryService;
	@Value("${server.port}")
	private int serverPort;

	public LotteryController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@GetMapping
	public List<List<Integer>> getLotteryNumbers(@RequestParam int column){
		System.err.println("New request has arrived at port "+serverPort);
		return lotteryService.draw(column);
	}
}
