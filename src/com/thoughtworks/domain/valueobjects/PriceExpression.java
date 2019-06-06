package com.thoughtworks.domain.valueobjects;

import java.util.List;

import com.thoughtworks.application.ApplicationException;
import com.thoughtworks.domain.entity.Input;
import com.thoughtworks.infrastructure.RomanValidator;
import com.thoughtworks.infrastructure.StringUtil;

public class PriceExpression implements Expression {

	private final Expression valueExpression;

	PriceExpression(Expression expression) {
		this.valueExpression = expression;
	}

	public void interpret(Context context) {
		// [how many Credits] is [glob prok Silver ?] to - "glob prok Silver is 68 Credits"
		String[] split = context.getInput().split(" is "); // [how many Credits] is [glob prok Silver ?]
		String[] preQuery = split[0].trim().split(" "); // [how many Credits]
		String unitsPlusCommodity = split[1].replace("?", "");
		unitsPlusCommodity = unitsPlusCommodity.trim(); // [glob prok Silver]
		String[] unitsPlusCommodityArr = unitsPlusCommodity.split(" ");
		interpretRoman(context, unitsPlusCommodityArr);
		String commodity = unitsPlusCommodityArr[unitsPlusCommodityArr.length - 1];
		Double rate = context.getTransaction().getRate(commodity);
		if (rate == null) {
			String message = String.format("Commodity {%s} was not mapped to a rate", commodity);
			List<Input> inputs = context.getTransaction().getUnmodifiableInputs();
			throw new ApplicationException(message, inputs.get(inputs.size() - 1).getText());
		}
		Long units = Long.valueOf(context.getOutput());
		context.getTransaction().addOutput(
				context.getTransaction().newOutput(OutputType.OK,
						unitsPlusCommodity + " is " + StringUtil.format(rate * units) + " " + preQuery[preQuery.length - 1]));
	}

	private void interpretRoman(Context context, String[] unitsPlusCommodityArr) {
		StringBuilder romanExp = new StringBuilder();
		for (int i = 0; i < unitsPlusCommodityArr.length - 1; i++) {
			String roman = context.getTransaction().getRoman(unitsPlusCommodityArr[i]);
			if (roman == null) {
				String message = String.format("Galactic unit {%s} was not mapped to a Roman", unitsPlusCommodityArr[i]);
				List<Input> inputs = context.getTransaction().getUnmodifiableInputs();
				throw new ApplicationException(message, inputs.get(inputs.size() - 1).getText());
			}
			romanExp.append(roman);
		}
		List<Input> inputs = context.getTransaction().getUnmodifiableInputs();
		RomanValidator.validate(romanExp.toString(), inputs.get(inputs.size() - 1).getText());
		context.setInput(romanExp.toString());
		valueExpression.interpret(context);
	}
}
