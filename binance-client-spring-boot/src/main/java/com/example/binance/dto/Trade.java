package com.example.binance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

// {"e":"trade","E":1631100132020,"s":"BTCUSDT","t":1045458832,"p":"46493.01000000","q":"0.07814000","b":7461421881,"a":7461422209,"T":1631100132019,"m":true,"M":true}
public class Trade {
	@JsonProperty("s")
	private String symbol;
	@JsonProperty("p")
	private String price;
	@JsonProperty("q")
	private String quantity;
	@JsonProperty("b")
	private String bidId;
	@JsonProperty("a")
	private String askId;
	@JsonProperty("t")
	private long timestamp;
	@JsonProperty("T")
	private long sequence;
	public Trade() {
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getBidId() {
		return bidId;
	}
	public void setBidId(String bidId) {
		this.bidId = bidId;
	}
	public String getAskId() {
		return askId;
	}
	public void setAskId(String askId) {
		this.askId = askId;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	@Override
	public String toString() {
		return "Trade [symbol=" + symbol + ", price=" + price + ", quantity=" + quantity + ", bidId=" + bidId
				+ ", askId=" + askId + ", timestamp=" + timestamp + ", sequence=" + sequence + "]";
	}
	
	
}
