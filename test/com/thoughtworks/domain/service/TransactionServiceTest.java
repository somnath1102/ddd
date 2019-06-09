package com.thoughtworks.domain.service;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.application.ApplicationException;
import com.thoughtworks.application.MessageTO;

public class TransactionServiceTest {

	TransactionService service = TransactionService.getDefaultService();

	@Test
	public void testGivenInput() {
		MessageTO request = new MessageTO();
		request.getMessageLines().add("glob is I");
		request.getMessageLines().add("prok is V");
		request.getMessageLines().add("pish is X");
		request.getMessageLines().add("tegj is L");
		request.getMessageLines().add("glob glob Silver is 34 Credits");
		request.getMessageLines().add("glob prok Gold is 57800 Credits");
		request.getMessageLines().add("pish pish Iron is 3910 Credits");
		request.getMessageLines().add("how much is pish tegj glob glob ?");
		request.getMessageLines().add("how many Credits is glob prok Silver ?");
		request.getMessageLines().add("how many Credits is glob prok Gold ?");
		request.getMessageLines().add("how many Credits is glob prok Iron ?");
		request.getMessageLines().add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
		MessageTO response = service.translate(request);
		assertTrue(response.getMessageLines().contains("pish tegj glob glob is 42"));
		assertTrue(response.getMessageLines().contains("glob prok Silver is 68 Credits"));
		assertTrue(response.getMessageLines().contains("glob prok Gold is 57800 Credits"));
		assertTrue(response.getMessageLines().contains("glob prok Iron is 782 Credits"));
		assertTrue(response.getMessageLines().contains("I have no idea what you are talking about"));
	}

	@Test
	public void testExceptionThrownWhenInputIsIncomplete() {
		MessageTO request = new MessageTO();
		request.getMessageLines().add("glob is I");
		request.getMessageLines().add("how many Credits is glob prok Silver ?");
		Assert.assertThrows(ApplicationException.class, () -> service.translate(request));
	}
	
	@Test
	public void testDoubleFormatting() {
		MessageTO request = new MessageTO();
		request.getMessageLines().add("glob is I");
		request.getMessageLines().add("prok is V");
		request.getMessageLines().add("pish is X");
		request.getMessageLines().add("tegj is L");
		request.getMessageLines().add("mad is M");
		request.getMessageLines().add("cat is C");
		// MCMXLIV - 1944
		request.getMessageLines().add("mad cat mad pish tegj glob prok Petrol is 2916 Credits");
		request.getMessageLines().add("how much is mad cat mad pish tegj glob prok ?");
		request.getMessageLines().add("how many Credits is prok Petrol ?");
		MessageTO response = service.translate(request);
		assertTrue(response.getMessageLines().contains("prok Petrol is 7.5 Credits"));
	}

}
