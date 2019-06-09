package com.thoughtworks.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple TO object for both request and response.
 * @author somnath
 *
 */
public class MessageTO implements Serializable {

	private static final long serialVersionUID = 1L;

	List<String> messageLines = new ArrayList<String>();

	public List<String> getMessageLines() {
		return messageLines;
	}

	public void setMessageLines(List<String> inputLines) {
		this.messageLines = inputLines;
	}

}
