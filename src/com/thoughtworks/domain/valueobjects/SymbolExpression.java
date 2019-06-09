package com.thoughtworks.domain.valueobjects;

import com.thoughtworks.domain.entity.Transaction;

/**
 * Captures the mapping between galactic and roman units.
 * 
 * @author somnath
 *
 */
public class SymbolExpression implements Expression {
	
	SymbolExpression() {
	}

	@Override
	public void interpret(Context context) {
		// glob is I
		String[] galactic_roman = context.getInput().split(" is ");
		Transaction transaction = context.getTransaction();
		transaction.newSymbol(galactic_roman[0].trim(), galactic_roman[1].trim());
		transaction.addSymbol(transaction.newSymbol(galactic_roman[0].trim(), galactic_roman[1].trim()));
	}
}