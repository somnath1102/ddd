package com.thoughtworks.domain.valueobjects;

import com.thoughtworks.domain.entity.Transaction;

/**
 * Is mutable.
 * Holds the input and transaction.
 * Transaction entity is populated from this context by the expressions.
 * output field is only used for the roman value calculation.
 * 
 * @author somnath
 *
 */
public class Context {

	private String input;
	private long output;
	private final Transaction transaction;

	public Context(String input, Transaction transaction) {
		this.input = input;
		this.transaction = transaction;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public long getOutput() {
		return output;
	}

	public void setOutput(long output) {
		this.output = output;
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
}
