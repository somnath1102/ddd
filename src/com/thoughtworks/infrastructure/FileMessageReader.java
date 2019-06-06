package com.thoughtworks.infrastructure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.thoughtworks.application.ApplicationException;
import com.thoughtworks.application.MessageTO;

public class FileMessageReader implements MessageReader {

	private static FileMessageReader singleton = new FileMessageReader();

	private FileMessageReader() {
	};

	@Override
	public MessageTO read(String path) throws ApplicationException {
		MessageTO request = new MessageTO();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
			String currLine;
			while ((currLine = br.readLine()) != null) {
				request.getMessageLines().add(currLine);
			}
		} catch (IOException e) {
			throw new ApplicationException(String.format("Error Reading path {%s}", path), "", e);
		}
		return request;
	}

	static MessageReader getInstance() {
		return singleton;
	}
}
