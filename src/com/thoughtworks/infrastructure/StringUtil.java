package com.thoughtworks.infrastructure;

public final class StringUtil {

	private StringUtil() {
	}

	public static String format(double d) {
		if (d == (long) d)
			return String.format("%d", (long) d);
		else
			return String.format("%s", d);
	}
}
