package com.vxml.tag;

import org.w3c.dom.Node;


public class TextTag extends Tag {


	public TextTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		String text = getTextContent();
		playTTS(text);
	}
}
