package com.vxml.tag;

import org.w3c.dom.Node;

public class PromptTag extends AbstractTag {

	public PromptTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
//		System.out.println("PROMPT:"+ getNode());
	}

}
