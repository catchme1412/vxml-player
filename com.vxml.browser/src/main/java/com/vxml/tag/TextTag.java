package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.core.browser.VxmlExecutionContext;
import com.vxml.tts.NativeCommand;

public class TextTag extends AbstractTag {

    private String text;
    
	public TextTag(Node node) {
		super(node);
		text = getNode().getTextContent().trim();
		
	}

	@Override
	public void execute() {
		if (!text.isEmpty() && VxmlExecutionContext.isTtsAllowed()) {
			try {
				new NativeCommand().speak(text);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString() {
	    return "TextTag:"+ text;
	}

}
