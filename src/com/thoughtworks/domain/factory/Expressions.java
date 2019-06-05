package com.thoughtworks.domain.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.domain.entity.Transaction;
import com.thoughtworks.domain.valueobjects.BaseValueExpression;
import com.thoughtworks.domain.valueobjects.Context;
import com.thoughtworks.domain.valueobjects.Expression;

public class Expressions {

	private Expressions() {

	}

	static Map<InputType, List<Expression>> mapping = new HashMap<>();

	static {
		mapping.put(InputType.SYMBOL, Collections.singletonList(symbolExpression()));
		mapping.put(InputType.RATE, Collections.singletonList(rateExpression()));
		mapping.put(InputType.PRICE_QUERY, Collections.singletonList(priceExpression()));
		mapping.put(InputType.VALUE_QUERY, valueExpressions());
		mapping.put(InputType.ERROR, Collections.singletonList(errorExpression()));
	}

	public static List<Expression> expressionFor(InputType type) {
		return mapping.get(type);
	}

	public static Expression thousandExpression() {
		return new ThousandExpression();
	}

	public static Expression hundredExpression() {
		return new HundredExpression();
	}

	public static Expression tenExpression() {
		return new TenExpression();
	}

	public static Expression oneExpression() {
		return new OneExpression();
	}

	public static Expression priceExpression() {
		return new PriceExpression();
	}

	public static Expression rateExpression() {
		return new RateExpression();
	}

	public static Expression symbolExpression() {
		return new SymbolExpression();
	}

	public static Expression errorExpression() {
		return new ErrorExpression();
	}

	public static List<Expression> valueExpressions() {
		List<Expression> valueExpressions = new ArrayList<>();
		valueExpressions.add(thousandExpression());
		valueExpressions.add(hundredExpression());
		valueExpressions.add(tenExpression());
		valueExpressions.add(oneExpression());
		return valueExpressions;
	}

	static class RateExpression implements Expression {

		@Override
		public void interpret(Context context) {

		}

	}

	static class ErrorExpression implements Expression {

		@Override
		public void interpret(Context context) {
			Transaction transaction = context.getTransaction();
			transaction.getOutputs().add(transaction.newOutput(OutputType.ERROR, "I have no idea what you are talking about"));
		}

	}

	static class SymbolExpression implements Expression {

		@Override
		public void interpret(Context context) {
			String[] galactic_roman = context.getInput().split("is");
			Transaction transaction = context.getTransaction();
			transaction.newSymbol(galactic_roman[0].trim(), galactic_roman[1].trim());
			transaction.getSymbols().add(transaction.newSymbol(galactic_roman[0].trim(), galactic_roman[1].trim()));
		}
	}

	static class PriceExpression implements Expression {

		@Override
		public void interpret(Context context) {
			// TODO Auto-generated method stub

		}
	}

	static class ThousandExpression extends BaseValueExpression {
		public String one() {
			return "M";
		}

		public String four() {
			return " ";
		}

		public String five() {
			return " ";
		}

		public String nine() {
			return " ";
		}

		public int multiplier() {
			return 1000;
		}
	}

	static class HundredExpression extends BaseValueExpression {
		public String one() {
			return "C";
		}

		public String four() {
			return "CD";
		}

		public String five() {
			return "D";
		}

		public String nine() {
			return "CM";
		}

		public int multiplier() {
			return 100;
		}
	}

	static class TenExpression extends BaseValueExpression {
		public String one() {
			return "X";
		}

		public String four() {
			return "XL";
		}

		public String five() {
			return "L";
		}

		public String nine() {
			return "XC";
		}

		public int multiplier() {
			return 10;
		}
	}

	static class OneExpression extends BaseValueExpression {
		public String one() {
			return "I";
		}

		public String four() {
			return "IV";
		}

		public String five() {
			return "V";
		}

		public String nine() {
			return "IX";
		}

		public int multiplier() {
			return 1;
		}
	}
}
