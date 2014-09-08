package com.vxml.tag;

import org.w3c.dom.Node;

public class ChoiceTag extends AbstractTag {

	public ChoiceTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		String dtmf = getAttribute("dtmf");
		String value = new DtmfInput().read();
		if (dtmf.equals(value)) {
		    String expr = getAttribute("expr");
		    Object target = executeScript(expr);
		    new VxmlDoc(target.toString()).play();
		}
	}


}
