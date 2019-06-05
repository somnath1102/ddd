package com.thoughtworks.domain.entity;

import java.util.List;

import com.thoughtworks.domain.factory.InputType;
import com.thoughtworks.domain.factory.OutputType;

public class Transaction {

	private Long id;
	private List<Input> inputs;
	private List<Output> outputs;
	private List<Symbol> symbols;
	private List<CommodityRate> rates;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Input> getInputs() {
		return inputs;
	}

	public void setInputs(List<Input> inputs) {
		for (Input input : inputs) {
			input.setTransaction(this);
		}
		this.inputs = inputs;
	}

	public List<Output> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<Output> outputs) {
		for (Output output : outputs) {
			output.setTransaction(this);
		}
		this.outputs = outputs;
	}

	public List<Symbol> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<Symbol> symbols) {
		for (Symbol symbol : symbols) {
			symbol.setTransaction(this);
		}
		this.symbols = symbols;
	}

	public List<CommodityRate> getRates() {
		return rates;
	}

	public void setRates(List<CommodityRate> rates) {
		for (CommodityRate rate : rates) {
			rate.setTransaction(this);
		}
		this.rates = rates;
	}

	public Symbol newSymbol(String galactic, String roman) {
		return new Symbol(galactic, roman);
	}

	public CommodityRate newRate(String commodity, Long rate) {
		return new CommodityRate(commodity, rate);
	}

	public Input newInput(InputType type, String text) {
		return new Input(type, text);
	}

	public Output newOutput(OutputType type, String text) {
		return new Output(type, text);
	}
}
