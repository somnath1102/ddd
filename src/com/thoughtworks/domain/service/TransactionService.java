package com.thoughtworks.domain.service;

import com.thoughtworks.application.MessageTO;

public interface TransactionService {

	public MessageTO translate(MessageTO request);

	static TransactionService getDefaultService() {
		return DefaultTransactionService.getInstance();
	}
}
