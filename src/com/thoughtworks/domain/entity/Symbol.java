package com.thoughtworks.domain.entity;

/**
 * Encapsulates the mapping between a galactic unit and its roman equivalent.
 * Belongs to a transaction(per input file).
 * 
 * @author somnath
 *
 */
public class Symbol {
	private Long id;
	private final String galatic;
	private final String roman;
	private final Transaction transaction;

	Symbol(String galactic, String roman, Transaction transaction) {
		this.galatic = galactic;
		this.roman = roman;
		this.transaction = transaction;
	}

	public String getGalatic() {
		return galatic;
	}

	public String getRoman() {
		return roman;
	}

}
