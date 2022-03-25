package com.example.application;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import com.example.domain.TradeEvent;

@SuppressWarnings("deprecation")
public class LegacyObserverApplication {

	public static void main(String[] args) {
		var observable = new ObservableTrade();
		Observer slowObserver = (o,event) -> {
			try {TimeUnit.SECONDS.sleep(5);}catch(Exception e) {}
			System.err.println("Slow Observer: "+event);	
		};
		Observer fastObserver = (o,event) -> {
			System.err.println("Fast Observer: "+event);	
		};
		observable.addObserver(slowObserver);
		observable.addObserver(fastObserver);
		var events = List.of(
				new TradeEvent("orcl", 123.45, 1_000),
				new TradeEvent("ibm", 100, 2_000),
				new TradeEvent("msft", 200, 3_000),
				new TradeEvent("orcl", 125.45, 4_000),
				new TradeEvent("ibm", 101, 5_000),
				new TradeEvent("msft", 201, 6_000)
		);
		events.forEach(observable::notifyObservers);
	}

}

@SuppressWarnings("deprecation")
class ObservableTrade extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}
	
}