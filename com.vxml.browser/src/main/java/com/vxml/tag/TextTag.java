package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.tts.NativeCommand;

public class TextTag extends AbstractTag {

	public TextTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		String text = getNode().getTextContent().trim();
		

		if (!text.isEmpty()
				&& (getNode().getParentNode().getNodeName().equals("foreach") || getNode()
						.getParentNode().getNodeName().equals("prompt") || getNode()
						.getParentNode().getNodeName().equals("audio"))
				&& !getNode().getParentNode().getNodeName().equals("script")) {
			try {
				new NativeCommand().speak(text);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
