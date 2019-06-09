package com.thoughtworks.infrastructure;

import com.thoughtworks.application.ApplicationException;
import com.thoughtworks.application.MessageTO;

/**
 * Common interface for reading a resource given a path.
 * 
 * @author somnath
 *
 */
public interface MessageReader {
	MessageTO read(String path) throws ApplicationException;

	static MessageReader getFileReader() {
		return FileMessageReader.getInstance();
	}
}
