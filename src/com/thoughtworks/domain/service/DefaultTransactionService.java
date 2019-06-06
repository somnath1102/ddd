package com.thoughtworks.domain.service;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.application.MessageTO;
import com.thoughtworks.domain.entity.Output;
import com.thoughtworks.domain.entity.Transaction;
import com.thoughtworks.domain.valueobjects.Context;
import com.thoughtworks.domain.valueobjects.Expressions;
import com.thoughtworks.domain.valueobjects.InputType;

public class DefaultTransactionService implements TransactionService {
	static DefaultTransactionService singleton = new DefaultTransactionService();

	private DefaultTransactionService() {
	}

	static DefaultTransactionService getInstance() {
		return singleton;
	}

	@Override
	public MessageTO translate(MessageTO request) {
		Transaction transaction = new Transaction();
		for (String input : request.getMessageLines()) {
			transaction.addInput(transaction.newInput(InputType.evaluate(input), input));
			Context context = new Context(input, transaction);
			Expressions.expressionFor(InputType.evaluate(input)).interpret(context);
		}
		MessageTO response = new MessageTO();
		List<String> outputs = new ArrayList<>();
		for (Output out : transaction.getUnmodifiableOutputs()) {
			outputs.add(out.getText());
		}
		response.setMessageLines(outputs);
		return response;
	}

}
