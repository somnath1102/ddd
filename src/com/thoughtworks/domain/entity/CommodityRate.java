package com.thoughtworks.domain.entity;

public class CommodityRate {

	private Long id;
	private Transaction transaction;
	private final String commodity;
	private final Long rate;

	CommodityRate(String commodity, Long rate) {
		this.commodity = commodity;
		this.rate = rate;
	}

	public String getCommodity() {
		return commodity;
	}

	public Long getRate() {
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
