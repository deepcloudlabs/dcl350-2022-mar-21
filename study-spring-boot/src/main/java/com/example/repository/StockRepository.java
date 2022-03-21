package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.entity.Stock;

public interface StockRepository extends MongoRepository<Stock, String>{

}
