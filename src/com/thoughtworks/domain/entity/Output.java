package com.thoughtworks.domain.entity;

import com.thoughtworks.domain.factory.OutputType;

public class Output {
	private Long id;
	private final OutputType type;
	private final String text;
	private Transaction transaction;

	Output(OutputType type, String text) {
		this.type = type;
		this.text = text;
	}

	public OutputType getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	public Long getId() {
		return id;
	}
	
	public Transaction getTransaction() {
		return transaction;
	}

	void setId(Long id) {
		this.id = id;
	}
	
	void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
