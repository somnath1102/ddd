package com.thoughtworks.domain.valueobjects;

import org.junit.Assert;
import org.junit.Test;

public class ExpressionsTest {

	@Test
	public void testErrorExpressionIsChosen() {
		Assert.assertTrue(Expressions.expressionFor(InputType.evaluate("invalid input")) instanceof ErrorExpression);
	}

	@Test
	public void testSymbolExpressionIsChosen() {
		Assert.assertTrue(Expressions.expressionFor(InputType.evaluate("glob is I")) instanceof SymbolExpression);
	}

	@Test
	public void testRateExpressionIsChosen() {
		Assert.assertTrue(Expressions.expressionFor(InputType.evaluate("pish pish Iron is 3910 Credits")) instanceof RateExpression);
	}

	@Test
	public void testValueExpressionIsChosen() {
		Assert.assertTrue(Expressions.expressionFor(InputType.evaluate("how much is pish tegj glob glob ?")) instanceof ValueExpression);
	}

	@Test
	public void testPriceExpressionIsChosen() {
		Assert.assertTrue(Expressions.expressionFor(InputType.evaluate("how many Credits is glob prok Iron ?")) instanceof PriceExpression);
	}
}
