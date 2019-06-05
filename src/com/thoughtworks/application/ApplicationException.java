package com.thoughtworks.application;

@SuppressWarnings("serial")
/**
 * Top level exception for the conversion program.
 */
public class ApplicationException extends Exception {
	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String s, Exception e) {
		super(s, e);
	}
}
