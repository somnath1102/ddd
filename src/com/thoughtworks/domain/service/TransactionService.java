package com.thoughtworks.domain.service;

import com.thoughtworks.application.MessageTO;

/**
 * Singleton to do the actual request processing.
 * Aggregates the operations for an input file.
 * 
 * @author mukherj9
 *
 */
public interface TransactionService {

	public MessageTO translate(MessageTO request);

	static TransactionService getDefaultService() {
		return DefaultTransactionService.getInstance();
	}
}
