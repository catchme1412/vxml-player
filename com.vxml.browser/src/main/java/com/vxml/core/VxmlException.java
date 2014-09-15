package com.vxml.core;

import java.net.URISyntaxException;

public class VxmlException extends RuntimeException {

	public VxmlException(URISyntaxException e) {
		super(e);
	}

}
