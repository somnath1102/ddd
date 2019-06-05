package com.thoughtworks.application;

import com.thoughtworks.domain.service.TransactionService;
import com.thoughtworks.infrastructure.MessageReader;

public class Main {

	private MessageReader reader = MessageReader.getFileReader();
	private TransactionService transactionService = TransactionService.getDefaultService();

	private static Main singleton = new Main();

	private Main() {

	}

	public static void main(String[] args) {
		try {
			MessageTO request = singleton.reader.read("C:\\workspaces\\input.txt");
			MessageTO response = singleton.transactionService.translate(request);
			for (String output : response.getMessageLines()) {
				System.out.println(output);
			}
		} catch (ApplicationException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(String.format("exiting application due to unforeseen: {}", e.getMessage()));
		}
	}

}
