package com.example.lottery.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

	@Bean
	public CacheManager cacheManager() {
		var cacheManager = new SimpleCacheManager();
		var lotteryCache = new ConcurrentMapCache("numbers");
		List<Cache> caches = new ArrayList<>();
		caches.add(lotteryCache);
		cacheManager.setCaches(caches);
		return cacheManager;
	}
}
