package com.thoughtworks.domain.entity;

public class CommodityRate {

	private Long id;
	private Transaction transaction;
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

	public Long getId() {
		return id;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	void setId(Long id) {
		this.id = id;
	}
}
