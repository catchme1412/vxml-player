package com.vxml.tag.logic;

import org.w3c.dom.Node;

import com.vxml.tag.AbstractTag;

public class ElseTag extends AbstractTag {

	public ElseTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
	    System.out.println(nodeToString(getNode()));
		executeChildNodes();
	}
}
