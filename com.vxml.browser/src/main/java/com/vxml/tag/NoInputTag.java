package com.vxml.tag;

import org.w3c.dom.Node;

public class NoInputTag extends AbstractTag {

	public NoInputTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
	    String name = getAttribute("name");
	    String expr = getAttribute("expr");
	    if (expr == null) {
	        executeScript("var " + name + ";");
	    } else {
	        executeScript(name + "=" + expr + ";");
	    }
	    isSkipTag = true;
		
	}


}
