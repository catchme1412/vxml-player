package com.vxml.tag;

import org.w3c.dom.Node;

public class NoMatchTag extends AbstractTag {

	public NoMatchTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		// if (VxmlPlayer.context.peekEvent() == null) {
		// isSkipTag = true;
		// }

	}

}
