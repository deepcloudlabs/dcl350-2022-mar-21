package com.example.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.entity.Stock;
import com.example.repository.StockRepository;

@Service
public class StockService {
	private final StockRepository stockRepository;
	
	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@EventListener
	public void listenPriceChange(Stock stock) {
		stockRepository.save(stock);
	}
}
