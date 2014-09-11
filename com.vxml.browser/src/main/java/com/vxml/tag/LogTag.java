package com.vxml.tag;

import org.w3c.dom.Node;

import com.sun.istack.internal.logging.Logger;

public class LogTag extends AbstractTag {

	private static final Logger log = Logger.getLogger(LogTag.class);

	public LogTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		String message = getNode().getTextContent();
		log.info(message);
	}

}
