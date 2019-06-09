package com.thoughtworks.domain.valueobjects;

import java.util.List;

import com.thoughtworks.application.ApplicationException;
import com.thoughtworks.domain.entity.Input;
import com.thoughtworks.infrastructure.RomanValidator;

/**
 * Captures the rate of a commodity by interpreting a rate input.
 * @author mukherj9
 *
 */
public class RateExpression implements Expression {

	private static final String IS = " is ";
	private final Expression valueExpression;

	RateExpression(Expression expression) {
		this.valueExpression = expression;
	}

	@Override
	public void interpret(Context context) {

		String[] commodityUnits_credit = context.getInput().split(IS); // [glob glob Silver] is [34 Credits]
		String[] galacticUnitsPlusCommodityExp = commodityUnits_credit[0].trim().split(" "); // [glob] [glob] [Silver]
		String[] credit_desc = commodityUnits_credit[1].trim().split(" "); // [34] [Credits]
		StringBuilder romanExp = new StringBuilder();
		for (int i = 0; i < galacticUnitsPlusCommodityExp.length - 1; i++) {
			String roman = context.getTransaction().getRoman(galacticUnitsPlusCommodityExp[i]);
			if (roman == null) {
				String message = String.format("Galactic unit {%s} was not mapped to a Roman", galacticUnitsPlusCommodityExp[i]);
				List<Input> inputs = context.getTransaction().getUnmodifiableInputs();
				throw new ApplicationException(message, inputs.get(inputs.size() - 1).getText());
			}
			romanExp.append(roman);
		}
		List<Input> inputs = context.getTransaction().getUnmodifiableInputs();
		RomanValidator.validate(romanExp.toString(), inputs.get(inputs.size() - 1).getText());
		context.setInput(romanExp.toString());
		valueExpression.interpret(context);
		Double rate = Double.valueOf(credit_desc[0].trim()) / context.getOutput();
		context.getTransaction().addRate(
				context.getTransaction().newRate(galacticUnitsPlusCommodityExp[galacticUnitsPlusCommodityExp.length - 1], rate));
	}

}