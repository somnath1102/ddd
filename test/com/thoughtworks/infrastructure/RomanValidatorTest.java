package com.thoughtworks.infrastructure;

import org.junit.Test;

import com.thoughtworks.application.ApplicationException;

public class RomanValidatorTest {
	
	@Test
	public void testMCMIIIIsValid() {
		RomanValidator.validate("MCMIII", "");
	}

	@Test(expected = ApplicationException.class)
	public void testIRepeated4timesSuccessivelyFails() {
		RomanValidator.validate("IIII", "");
	}

	@Test(expected = ApplicationException.class)
	public void testXRepeated4timesSuccessivelyFails() {
		RomanValidator.validate("XXXX", "");
	}

	@Test(expected = ApplicationException.class)
	public void testCRepeated4timesSuccessivelyFails() {
		RomanValidator.validate("CCCC", "");
	}

	@Test(expected = ApplicationException.class)
	public void testMRepeated4timesSuccessivelyFails() {
		RomanValidator.validate("MMMM", "");
	}

	@Test
	public void testXXXIXPasses() {
		RomanValidator.validate("XXXIX", "");
	}

	@Test(expected = ApplicationException.class)
	public void testDRepeatedFails() {
		RomanValidator.validate("DD", "");
	}

	@Test(expected = ApplicationException.class)
	public void testLRepeatedFails() {
		RomanValidator.validate("LL", "");
	}

	@Test(expected = ApplicationException.class)
	public void testVRepeatedFails() {
		RomanValidator.validate("VV", "");
	}

	@Test
	public void testISubtractedFromVPasses() {
		RomanValidator.validate("IV", "");
	}

	@Test
	public void testISubtractedFromXPasses() {
		RomanValidator.validate("IX", "");
	}

	@Test(expected = ApplicationException.class)
	public void testISubtractedFromMFails() {
		RomanValidator.validate("IM", "");
	}

	@Test
	public void testXSubtractedFromLPasses() {
		RomanValidator.validate("XL", "");
	}

	@Test
	public void testXSubtractedFromCPasses() {
		RomanValidator.validate("XC", "");
	}

	@Test(expected = ApplicationException.class)
	public void testXSubtractedFromMFails() {
		RomanValidator.validate("XM", "");
	}

	@Test
	public void testCSubtractedFromDPasses() {
		RomanValidator.validate("CD", "");
	}

	@Test
	public void testCSubtractedFromMPasses() {
		RomanValidator.validate("CM", "");
	}

	@Test(expected = ApplicationException.class)
	public void testCSubtractedFromJFails() {
		RomanValidator.validate("CJ", "");
	}

	@Test(expected = ApplicationException.class)
	public void testVSubtractedFromXFails() {
		RomanValidator.validate("VX", "");
	}

	@Test(expected = ApplicationException.class)
	public void testLSubtractedFromCFails() {
		RomanValidator.validate("LC", "");
	}

	@Test(expected = ApplicationException.class)
	public void testDSubtractedFromMFails() {
		RomanValidator.validate("DM", "");
	}
	
	@Test(expected = ApplicationException.class)
	public void testIISubtractedFromVFails() {
		RomanValidator.validate("IIV", "");
	}
}
