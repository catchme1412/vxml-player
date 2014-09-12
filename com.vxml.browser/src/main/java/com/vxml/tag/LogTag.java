package com.vxml.tag;

import java.util.logging.Logger;

import org.w3c.dom.Node;

public class LogTag extends AbstractTag {

	private static final Logger log = Logger.getAnonymousLogger();

	public LogTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
	    
		String message = getNode().getTextContent();
		log.info(message);
	}

}
