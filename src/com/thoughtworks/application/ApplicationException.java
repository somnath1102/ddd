package com.thoughtworks.application;

@SuppressWarnings("serial")
/**
 * Top level exception for the conversion program.
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
