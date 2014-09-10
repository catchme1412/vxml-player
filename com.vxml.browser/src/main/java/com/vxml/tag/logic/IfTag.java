package com.vxml.tag.logic;

import org.w3c.dom.Node;

import com.vxml.tag.AbstractTag;

public class IfTag extends AbstractTag {

	public IfTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		ifTagLevel++;
		String cond = getAttribute("cond");
		Boolean isIfConditionTrue = (Boolean) executeScript(cond + ";");
		executeScript("var IF_CONDITION_LEVEL_" + ifTagLevel + "=" + isIfConditionTrue + ";");
		if (isIfConditionTrue) {
			isSkipTag = false;
		} else {
			isSkipTag = true;
		}
	}

}
