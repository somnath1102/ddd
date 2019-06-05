package com.thoughtworks.infrastructure;

public class TransactionRegex {
	public static final String SYMBOL_INPUT_REGEX = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$";
	public static final String RATE_INPUT_REGEX = "^([a-z]+) is ([I|V|X|L|C|D|M])$";
	public static final String PRICE_QUERY_REGEX = "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";
	public static final String VALUE_QUERY_REGEX = "^how much is ((?:\\w+[^0-9] )+)\\?$";
}
