package com.vxml.tag;

import java.io.IOException;

import org.w3c.dom.Node;

import com.vxml.tts.NativeCommand;

public class ValueTag extends AbstractTag {

	public ValueTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
	    if (getNode().getParentNode().getNodeName().equals("prompt")) {
	        String expr = getAttribute("expr");
	        Object value = executeScript(expr);
	        try {
				new NativeCommand().speak((String) value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

}
