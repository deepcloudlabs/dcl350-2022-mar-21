package com.example.controller;

import java.util.function.Supplier;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.entity.Stock;
import com.example.repository.StockRepository;

@RestController
@RequestScope
@RequestMapping("/stocks")
@Validated
@CrossOrigin
public class StockRestController {

	private static final Supplier<IllegalArgumentException> CANNOT_FIND_STOCK =
	                        () -> new IllegalArgumentException("Cannot find stock.");
	private final StockRepository stockRepository;
	
	public StockRestController(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@GetMapping("{symbol}")
	public Stock getStockBySymbol(@PathVariable String symbol) {
		return stockRepository.findById(symbol)
				              .orElseThrow(CANNOT_FIND_STOCK);
	}
}
