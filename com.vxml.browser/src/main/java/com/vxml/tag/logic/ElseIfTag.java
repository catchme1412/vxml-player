package com.vxml.tag.logic;

import org.w3c.dom.Node;

import com.vxml.tag.AbstractTag;

public class ElseIfTag extends AbstractTag {

	public ElseIfTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		String cond = getAttribute("cond");
		Boolean isTrue = (Boolean)executeScript(cond + ";");
		if (isTrue) {
            executeChildNodes();
        }
	}


}
