package com.thoughtworks.domain.valueobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.application.ApplicationException;
import com.thoughtworks.domain.entity.Transaction;

public class ExpressionTest {

	@Test
	public void testSymbolExpressionIsEvaluated() {
		Context context = new Context("prok is V", new Transaction());
		assertNull(context.getTransaction().getRoman("prok"));
		Expressions.symbolExpression().interpret(context);
		assertEquals("V", context.getTransaction().getRoman("prok"));
	}

	@Test
	public void testRateExpressionFailsWithoutSymbols() {
		Transaction transaction = new Transaction();
		Context context = setupContext(InputType.RATE, "glob glob Silver is 34 Credits", transaction);
		Assert.assertThrows(ApplicationException.class, () -> Expressions.rateExpression().interpret(context));
	}

	@Test
	public void testPriceExpressionFailsWithoutRate() {
		Transaction transaction = new Transaction();
		Context context = setupContext(InputType.SYMBOL, "glob is I", transaction);
		Expressions.symbolExpression().interpret(context);
		Context pContext = setupContext(InputType.PRICE_QUERY, "how many Credits is glob glob Silver ?", transaction);
		Assert.assertThrows(ApplicationException.class, () -> Expressions.priceExpression().interpret(pContext));
	}

	@Test
	public void testValueExpressionIsEvaluated() {
		Transaction transaction = new Transaction();
		Context context = setupContext(InputType.SYMBOL, "glob is I", transaction);
		Expressions.symbolExpression().interpret(context);
		context = setupContext(InputType.VALUE_QUERY, "how much is glob glob ?", transaction);
		Expressions.valueExpression().interpret(context);
		assertEquals("glob glob is 2", context.getTransaction().getUnmodifiableOutputs().get(0).getText());
	}

	@Test
	public void testValueExpressionFailsIfNoSymbol() {
		Transaction transaction = new Transaction();
		Context context = setupContext(InputType.VALUE_QUERY, "how much is glob glob ?", transaction);
		Assert.assertThrows(ApplicationException.class, () -> Expressions.valueExpression().interpret(context));
	}

	@Test
	public void testPriceExpressionIsEvaluated() {
		Transaction transaction = new Transaction();
		Context context = setupContext(InputType.SYMBOL, "glob is I", transaction);
		Expressions.symbolExpression().interpret(context);
		context = setupContext(InputType.RATE, "glob glob Silver is 34 Credits", transaction);
		Expressions.rateExpression().interpret(context);
		context = setupContext(InputType.PRICE_QUERY, "how many Credits is glob glob Silver ?", transaction);
		Expressions.priceExpression().interpret(context);
		assertEquals("glob glob Silver is 34 Credits", context.getTransaction().getUnmodifiableOutputs().get(0).getText());
	}

	@Test
	public void testRateExpressionIsEvaluated() {
		Transaction transaction = new Transaction();
		Context context = setupContext(InputType.SYMBOL, "glob is I", transaction);
		Expressions.symbolExpression().interpret(context);
		context = setupContext(InputType.RATE, "glob glob Silver is 34 Credits", transaction);
		Expressions.rateExpression().interpret(context);
		assertEquals(Double.valueOf(17), context.getTransaction().getRate("Silver"));
	}

	private Context setupContext(InputType type, String input, Transaction transaction) {
		transaction.addInput(transaction.newInput(type, input));
		Context context = new Context(input, transaction);
		return context;
	}

}
