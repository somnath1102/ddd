package com.thoughtworks.infrastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.application.ApplicationException;

/**
 * Implements logic to validate roman expression. Candidate for applying the
 * interpreter-pattern to realise this too?
 *
 */
public final class RomanValidator {

	private static Map<String, Integer> valueMap = new HashMap<String, Integer>();
	private static Map<String, Set<String>> subtractionMap = new HashMap<String, Set<String>>();
	private static Set<String> maxThreeSuccessiveOnly = new HashSet<String>();
	private static Set<String> nonRepeatable = new HashSet<String>();
	private static Set<String> nonSubtractable = nonRepeatable;

	private RomanValidator() {
	}

	static {
		valueMap.put("I", 1);
		valueMap.put("V", 5);
		valueMap.put("X", 10);
		valueMap.put("L", 50);
		valueMap.put("C", 100);
		valueMap.put("D", 500);
		valueMap.put("M", 1000);
		maxThreeSuccessiveOnly.add("I");
		maxThreeSuccessiveOnly.add("X");
		maxThreeSuccessiveOnly.add("C");
		maxThreeSuccessiveOnly.add("M");
		nonRepeatable.add("D");
		nonRepeatable.add("L");
		nonRepeatable.add("V");
		Set<String> subtractablesI = new HashSet<String>();
		subtractablesI.add("V");
		subtractablesI.add("X");
		subtractionMap.put("I", subtractablesI);
		Set<String> subtractablesX = new HashSet<String>();
		subtractablesX.add("L");
		subtractablesX.add("C");
		subtractionMap.put("X", subtractablesX);
		Set<String> subtractablesC = new HashSet<String>();
		subtractablesC.add("D");
		subtractablesC.add("M");
		subtractionMap.put("C", subtractablesC);
	}
	
	/**
	 * Throws and ApplicationException with details if not valid.
	 * 
	 * @param romanValue to be parsed
	 * @param input kept to identify the source line in error in the exception
	 */
	public static void validate(String romanValue, String input) {
		String curr = "";
		ConsecutiveTracker tracker = new ConsecutiveTracker();
		for (int i = 0; i < romanValue.length(); i++) {
			String prev = i > 0 ? romanValue.charAt(i - 1) + "" : "";
			curr = romanValue.charAt(i) + "";
			if (!valueMap.keySet().contains(curr))
				throw new ApplicationException(curr + " is not a roman character", input);
			if (curr.equals(prev) && nonRepeatable.contains(curr))
				throw new ApplicationException(curr + " cannot be repeated", input);
			if (!tracker.getRoman().equals(curr)) {
				tracker.setRoman(curr);
				tracker.setCount(1);
			} else {
				tracker.setCount(tracker.getCount() + 1);
			}
			if (maxThreeSuccessiveOnly.contains(curr) && (tracker.getCount() > 3))
				throw new ApplicationException(curr + " cannot be repeated more than " + 3 + " times", input);
			if (valueMap.containsKey(prev) && (valueMap.get(curr) > valueMap.get(prev))) {
				if (nonSubtractable.contains(prev) || (subtractionMap.containsKey(prev) && !subtractionMap.get(prev).contains(curr)))
					throw new ApplicationException(prev + " cannot be subtracted from " + curr, input);
				if (i > 1) {
					String prevOfPrev = romanValue.charAt(i - 2) + "";
					// check if both preceding are smaller than curr
					if (valueMap.containsKey(prevOfPrev) && (valueMap.get(curr) > valueMap.get(prevOfPrev)))
						throw new ApplicationException(prevOfPrev + prev + " cannot be subtracted from " + curr
								+ " .Only one smaller value can be subtracted", input);
				}
			}
		}
	}

	private static class ConsecutiveTracker {
		String roman = "";
		int count = 0;

		String getRoman() {
			return roman;
		}

		void setRoman(String roman) {
			this.roman = roman;
		}

		int getCount() {
			return count;
		}

		void setCount(int count) {
			this.count = count;
		}
	}
}
