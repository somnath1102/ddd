package com.thoughtworks.domain.entity;

import com.thoughtworks.domain.valueobjects.OutputType;

/**
 * Captures an output in the transaction. Created for auditing.
 * 
 * @author somnath
 *
 */
public class Output {
	private Long id;
	private final OutputType type;
	private final String text;
	private final Transaction transaction;

	Output(OutputType type, String text, Transaction transaction) {
		this.type = type;
		this.text = text;
		this.transaction = transaction;
	}

	public String getText() {
		return text;
	}

}
