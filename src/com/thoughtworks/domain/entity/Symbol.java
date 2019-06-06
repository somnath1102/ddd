package com.thoughtworks.domain.entity;

public class Symbol {
	private Long id;
	private final String galatic;
	private final String roman;
	private Transaction transaction;

	Symbol(String galactic, String roman, Transaction transaction) {
		this.galatic = galactic;
		this.roman = roman;
		this.transaction = transaction;
	}

	public String getGalatic() {
		return galatic;
	}

	public String getRoman() {
		return roman;
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
