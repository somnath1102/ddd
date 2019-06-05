package com.thoughtworks.infrastructure;

import com.thoughtworks.application.ApplicationException;
import com.thoughtworks.application.MessageTO;

public interface MessageReader {
	MessageTO read(String path) throws ApplicationException;

	static MessageReader getFileReader() {
		return FileMessageReader.getInstance();
	}
}
