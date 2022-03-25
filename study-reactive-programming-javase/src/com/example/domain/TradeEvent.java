package com.example.domain;

public class TradeEvent {
	private String symbol;
	private double price;
	private double quantity;

	public TradeEvent() {
	}

	public TradeEvent(String symbol, double price, double quantity) {
		this.symbol = symbol;
		this.price = price;
		this.quantity = quantity;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "TradeEvent [symbol=" + symbol + ", price=" + price + ", quantity=" + quantity + "]";
	}

}
