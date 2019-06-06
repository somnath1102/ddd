package com.thoughtworks.domain.valueobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory for expression objects
 * @author somnath
 *
 */
public class Expressions {

	private Expressions() {

	}

	static Map<InputType, Expression> mapping = new HashMap<>();

	static {
		mapping.put(InputType.SYMBOL, symbolExpression());
		mapping.put(InputType.RATE, rateExpression());
		mapping.put(InputType.PRICE_QUERY, priceExpression());
		mapping.put(InputType.VALUE_QUERY, valueExpression());
		mapping.put(InputType.ERROR, errorExpression());
	}

	public static Expression expressionFor(InputType type) {
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
		return new PriceExpression(valueExpression());
	}

	public static Expression rateExpression() {
		return new RateExpression(valueExpression());
	}

	public static Expression symbolExpression() {
		return new SymbolExpression();
	}

	public static Expression errorExpression() {
		return new ErrorExpression();
	}

	public static Expression valueExpression() {
		List<Expression> valueExpressions = new ArrayList<>();
		valueExpressions.add(thousandExpression());
		valueExpressions.add(hundredExpression());
		valueExpressions.add(tenExpression());
		valueExpressions.add(oneExpression());
		return new ValueExpression(valueExpressions);
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
