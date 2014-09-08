package com.vxml.tag;

import org.w3c.dom.Node;

public class AssignTag extends AbstractTag {

	public AssignTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
	    String name = getAttribute("name");
	    String expr = getAttribute("expr");
	    if (expr == null) {
	        executeScript("var " + name + ";");
	    } else {
	        executeScript("var " + name + "=" + expr + ";");
	    }
		
	}


}
