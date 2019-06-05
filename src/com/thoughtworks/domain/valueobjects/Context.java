package com.thoughtworks.domain.valueobjects;

import com.thoughtworks.domain.entity.Transaction;

public class Context {

	private String input;
	private String output;
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

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
}
