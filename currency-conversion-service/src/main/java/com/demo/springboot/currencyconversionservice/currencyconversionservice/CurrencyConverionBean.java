package com.demo.springboot.currencyconversionservice.currencyconversionservice;

import java.math.BigDecimal;

public class CurrencyConverionBean {

	private Long Id;
	private String currencyFrom;
	private String currencyTo;
	private BigDecimal conversionMultiple;
	private BigDecimal requestedQuantity;
	private BigDecimal totalCalculatedAmount;
	private int port;
	
	
	
	public CurrencyConverionBean() {

	}
	
	public CurrencyConverionBean(Long id, String currencyFrom, String currencyTo, BigDecimal conversionMultiple,
			BigDecimal requestedQuantity, BigDecimal totalCalculatedAmount, int port) {
		super();
		Id = id;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.conversionMultiple = conversionMultiple;
		this.requestedQuantity = requestedQuantity;
		this.totalCalculatedAmount = totalCalculatedAmount;
		this.port = port;
	}



	public Long getId() {
		return Id;
	}



	public void setId(Long id) {
		Id = id;
	}



	public String getCurrencyFrom() {
		return currencyFrom;
	}



	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}



	public String getCurrencyTo() {
		return currencyTo;
	}



	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}



	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}



	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}



	public BigDecimal getRequestedQuantity() {
		return requestedQuantity;
	}



	public void setRequestedQuantity(BigDecimal requestedQuantity) {
		this.requestedQuantity = requestedQuantity;
	}



	public BigDecimal getTotalCalculatedAmount() {
		return totalCalculatedAmount;
	}



	public void setTotalCalculatedAmount(BigDecimal totalCalculatedAmount) {
		this.totalCalculatedAmount = totalCalculatedAmount;
	}



	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}
	
	
	
	
	
}
