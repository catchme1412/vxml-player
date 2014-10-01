package com.vxml.tag;

import org.w3c.dom.Node;


public abstract class TagWithCondition extends Tag {

	public TagWithCondition(Node node) {
		super(node);
	}

	private String cond;

	public String getCondition() {
		return cond;
	}

}
