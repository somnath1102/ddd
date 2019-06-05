package com.thoughtworks.domain.entity;

import com.thoughtworks.domain.factory.InputType;

public class Input {
	private Long id;
	private final InputType type;
	private final String text;
	private Transaction transaction;

	Input(InputType type, String text) {
		this.type = type;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public InputType getType() {
		return type;
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
