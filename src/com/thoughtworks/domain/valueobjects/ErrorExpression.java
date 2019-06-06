package com.thoughtworks.domain.valueobjects;

import com.thoughtworks.domain.entity.Transaction;

public class ErrorExpression implements Expression {

	@Override
	public void interpret(Context context) {
		Transaction transaction = context.getTransaction();
		transaction.addOutput(transaction.newOutput(OutputType.ERROR, "I have no idea what you are talking about"));
	}

}
