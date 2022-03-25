package com.example.application;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import com.example.domain.TradeEvent;

public class ReactiveApplication {

	public static void main(String[] args) {
		var publisher = new SubmissionPublisher<TradeEvent>();
		publisher.subscribe(new AlgoTrader());
		publisher.subscribe(new SignalProcessor());
		var events = List.of(
				new TradeEvent("orcl", 123.45, 1_000),
				new TradeEvent("ibm", 100, 2_000),
				new TradeEvent("msft", 200, 3_000),
				new TradeEvent("orcl", 125.45, 4_000),
				new TradeEvent("ibm", 101, 5_000),
				new TradeEvent("msft", 201, 6_000)
		);
		events.forEach(publisher::submit);
		try {TimeUnit.SECONDS.sleep(60);}catch(Exception e) {}
		publisher.close();
		System.err.println("Application is done.");
	}

}

class AlgoTrader implements Flow.Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		try {TimeUnit.SECONDS.sleep(5);}catch(Exception e) {}
		System.err.println("AlgoTrader: "+event);		
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println("An error has occured in AlgoTrader: "+throwable.getMessage());
		
	}

	@Override
	public void onComplete() {
		System.err.println("AlgoTrader has completed!");				
	}
	
}

class SignalProcessor implements Flow.Subscriber<TradeEvent> {
	
	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
		
	}
	
	@Override
	public void onNext(TradeEvent event) {
		System.err.println("SignalProcessor: "+event);		
		this.subscription.request(1);
	}
	
	@Override
	public void onError(Throwable throwable) {
		System.err.println("An error has occured in SignalProcessor: "+throwable.getMessage());
		
	}
	
	@Override
	public void onComplete() {
		System.err.println("SignalProcessor has completed!");				
	}
	
}