package com.thoughtworks.domain.entity;

/**
 * Captures the commodity rate in a transaction. 
 * 
 * @author somnath
 *
 */
public class CommodityRate {

	private Long id;
	private final Transaction transaction;
	private final String commodity;
	private final Double rate;

	CommodityRate(String commodity, Double rate, Transaction transaction) {
		this.commodity = commodity;
		this.rate = rate;
		this.transaction = transaction;
	}

	public String getCommodity() {
		return commodity;
	}

	public Double getRate() {
		return rate;
	}
	
}
