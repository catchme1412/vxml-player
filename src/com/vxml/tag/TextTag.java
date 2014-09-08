package com.vxml.tag;

import org.w3c.dom.Node;

public class TextTag extends AbstractTag {

	public TextTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		if (!getNode().getTextContent().trim().isEmpty() && getNode().getParentNode().getNodeName().equals("prompt")) {
			System.out.println(getNode().getTextContent().trim());
		}
		
	}

	

}
