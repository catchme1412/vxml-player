package com.vxml.tag;

import org.w3c.dom.Node;

public class LinkTag extends AbstractTag {

	public LinkTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		setSkipExecute(true);
	}


}
