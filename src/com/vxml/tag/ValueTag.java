package com.vxml.tag;

import org.w3c.dom.Node;

public class ValueTag extends AbstractTag {

	public ValueTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
	    if (getNode().getParentNode().getNodeName().equals("prompt")) {
	        String expr = getAttribute("expr");
	        Object value = executeScript(expr + ";");
	        System.out.println(value);
	    }
	}

}
