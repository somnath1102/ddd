package com.thoughtworks.domain.entity;

import com.thoughtworks.domain.valueobjects.InputType;

/**
 * Captures an input in the transaction.
 * Created for auditing.
 * 
 * @author mukherj9
 *
 */
public class Input {
	private Long id;
	private final InputType type;
	private final String text;
	private final Transaction transaction;

	Input(InputType type, String text, Transaction transaction) {
		this.type = type;
		this.text = text;
		this.transaction = transaction;
	}

	public String getText() {
		return text;
	}
	
}
