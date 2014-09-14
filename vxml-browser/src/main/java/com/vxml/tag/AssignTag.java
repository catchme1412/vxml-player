package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.core.browser.VxmlBrowser;

public class AssignTag extends AbstractTag {

	public AssignTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
	    String name = getAttribute("name");
	    String expr = getAttribute("expr");
	    if (expr == null) {
	        VxmlBrowser.getContext().executeScript("var " + name + ";");
	    } else {
	        VxmlBrowser.getContext().executeScript(name + "=" + expr + ";");
	    }
		
	}


}
