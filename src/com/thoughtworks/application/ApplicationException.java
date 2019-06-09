package com.thoughtworks.application;

@SuppressWarnings("serial")
/**
 * Unchecked exception for the conversion program.
 * Captures the input line if possible.
 * Fail-fast approach
 */
public class ApplicationException extends RuntimeException {

	private final String input;

	public ApplicationException(String message, String input) {
		super(message);
		this.input = input;
	}

	public ApplicationException(String message, String input, Exception e) {
		super(message, e);
		this.input = input;
	}

	public String getInput() {
		return input;
	}
}
