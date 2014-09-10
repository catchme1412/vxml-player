package com.vxml.tag.logic;

import org.w3c.dom.Node;

import com.vxml.tag.AbstractTag;

public class ElseIfTag extends AbstractTag {

	public ElseIfTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		if(isCorrespondingIfConditionIsTrue()) {
			isSkipTag = true;
		} else {
			String cond = getAttribute("cond");
			Boolean isTrue = (Boolean)executeScript(cond + ";");
			executeScript("var IF_CONDITION_LEVEL_" + ifTagLevel + "=" + isTrue + ";");
			if (isTrue) {
				isSkipTag = false;
			} else {
				isSkipTag = true;
			}
		}
	}

}
