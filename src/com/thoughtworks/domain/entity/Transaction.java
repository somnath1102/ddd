package com.thoughtworks.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.domain.valueobjects.InputType;
import com.thoughtworks.domain.valueobjects.OutputType;

/**
 * The root entity which encapsulates domain info for each input file.
 * This can be persisted to track all info for each input file.
 * Also used to calculate the response from its encapsulated 'outputs'.
 * 
 * @author somnath
 *
 */
public class Transaction {

	@SuppressWarnings("unused")
	private Long id;
	private List<Input> inputs = new ArrayList<>();
	private List<Output> outputs = new ArrayList<>();
	private List<Symbol> symbols = new ArrayList<>();
	private List<CommodityRate> rates = new ArrayList<>();

	public List<Output> getUnmodifiableOutputs() {
		return new ArrayList<>(outputs);
	}
	
	public List<Input> getUnmodifiableInputs() {
		return new ArrayList<>(inputs);
	}

	public Symbol newSymbol(String galactic, String roman) {
		return new Symbol(galactic, roman, this);
	}

	public CommodityRate newRate(String commodity, Double rate) {
		return new CommodityRate(commodity, rate, this);
	}

	public Input newInput(InputType type, String text) {
		return new Input(type, text, this);
	}

	public Output newOutput(OutputType type, String text) {
		return new Output(type, text, this);
	}

	public boolean addSymbol(Symbol symbol) {
		return symbols.add(symbol);
	}

	public String getRoman(String galactic) {
		for (Symbol symbol : symbols) {
			if (symbol.getGalatic().equals(galactic))
				return symbol.getRoman();
		}
		return null;
	}

	public void addRate(CommodityRate newRate) {
		rates.add(newRate);
	}

	public Double getRate(String commodity) {
		for (CommodityRate rate : rates) {
			if (rate.getCommodity().equals(commodity))
				return rate.getRate();
		}
		return null;
	}

	public void addInput(Input newInput) {
		inputs.add(newInput);
	}

	public void addOutput(Output newOutput) {
		outputs.add(newOutput);
	}
}
