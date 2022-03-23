package com.example.binance.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.binance.dto.Ticker;

@Service
public class BinanceRestCientService {
	@Value("${binanceRestApiUrl}") // application.properties: binanceRestApiUrl=https:/api.binance.com/...
	private String binanceRestApiUrl;
	
	@Scheduled(fixedRate = 1_000)
	public void callBinanceRestApi() {
		var restTemplate = new RestTemplate();
		var ticker = restTemplate.getForEntity(binanceRestApiUrl, Ticker.class).getBody();
		System.err.println(ticker);
	}
}
