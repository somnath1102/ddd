package com.thoughtworks.domain.valueobjects;

/**
 * Interprets the input captured in the context to do the conversion for an query or capture 
 * the information for non-query inputs in the Transaction instance associated to the context.
 * 
 * @author somnath
 *
 */
public interface Expression {
	public void interpret(Context context);
}
