package com.thoughtworks.domain.valueobjects;

import com.thoughtworks.infrastructure.TransactionRegex;

public enum InputType {
	SYMBOL, RATE, VALUE_QUERY, PRICE_QUERY, ERROR;

	public static InputType evaluate(String input) {
		
		if (input.matches(TransactionRegex.SYMBOL_INPUT_REGEX)) {
			return SYMBOL;
		} else if (input.matches(TransactionRegex.RATE_INPUT_REGEX)) {
			return RATE;
		} else if (input.matches(TransactionRegex.PRICE_QUERY_REGEX)) {
			return PRICE_QUERY;
		} else if (input.matches(TransactionRegex.VALUE_QUERY_REGEX)) {
			return VALUE_QUERY;
		} else {
			return ERROR;
		}
	}
}
