package com.thoughtworks.domain.valueobjects;

import java.util.List;

import com.thoughtworks.application.ApplicationException;
import com.thoughtworks.domain.entity.Input;
import com.thoughtworks.infrastructure.RomanValidator;
import com.thoughtworks.infrastructure.TransactionRegex;

/**
 * Calculates the value in decimal from a galactic unit expression using already captured information.
 * @author mukherj9
 *
 */
public class ValueExpression implements Expression {

	private final List<Expression> expressions;

	ValueExpression(List<Expression> expressions) {
		this.expressions = expressions;
	}

	@Override
	public void interpret(Context context) {
		String units = null;
		boolean isValueQuery = context.getInput().matches(TransactionRegex.VALUE_QUERY_REGEX);
		if (isValueQuery) {
			// [how much] is [pish tegj glob glob ?] - pish tegj glob glob is 42
			String[] split = context.getInput().split(" is ");
			units = split[1].replace("?", "").trim();
			String[] galacticUnitsExp = units.split(" ");
			// candidate for refactor with RateExpression
			StringBuilder romanExp = new StringBuilder();
			for (String gUnit : galacticUnitsExp) {
				String roman = context.getTransaction().getRoman(gUnit);
				if (roman == null) {
					String message = String.format("Galactic unit {%s} was not mapped to a Roman", gUnit);
					List<Input> inputs = context.getTransaction().getUnmodifiableInputs();
					throw new ApplicationException(message, inputs.get(inputs.size() - 1).getText());
				}
				romanExp.append(roman);
			}
			List<Input> inputs = context.getTransaction().getUnmodifiableInputs();
			RomanValidator.validate(romanExp.toString(), inputs.get(inputs.size() - 1).getText());
			context.setInput(romanExp.toString());
		}
		for (Expression expression : expressions) {
			expression.interpret(context);
		}
		if (isValueQuery) {
			context.getTransaction().addOutput(context.getTransaction().newOutput(OutputType.OK, units + " is " + context.getOutput()));
		}
	}

}
