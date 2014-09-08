package com.vxml.tag;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PromptTag extends AbstractTag {

	public PromptTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
//		System.out.println("PROMPT:"+ getNode());
	}

}
