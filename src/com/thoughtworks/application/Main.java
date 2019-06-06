package com.thoughtworks.application;

import com.thoughtworks.domain.service.TransactionService;
import com.thoughtworks.infrastructure.MessageReader;

public class Main {

	private static final String FILE_LOCATION = "C:\\workspaces\\input.txt";
	private MessageReader reader = MessageReader.getFileReader();
	private TransactionService transactionService = TransactionService.getDefaultService();

	private static Main singleton = new Main();

	private Main() {

	}

	public static void main(String[] args) {
		MessageTO response = new MessageTO();
		try {
			MessageTO request = singleton.reader.read(FILE_LOCATION);
			response = singleton.transactionService.translate(request);
		} catch (ApplicationException e) {
			e.printStackTrace();
			response.getMessageLines().add(String.format("Request not valid - {%s} for input line {%s}", e.getMessage(), e.getInput()));
		} catch (Exception e) {
			e.printStackTrace();
			response.getMessageLines().add(String.format("Server error - %s ", e.getMessage()));
		} finally {
			for (String output : response.getMessageLines()) {
				System.out.println(output);
			}
		}
	}

}
