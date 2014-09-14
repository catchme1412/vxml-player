package com.vxml.tag;

import org.w3c.dom.Node;

public class CommentTag extends AbstractTag {

	public CommentTag(Node node) {
		super(node);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startTag() {
		setSkipExecute(true);
	}

	@Override
	public void execute() {
	}

	@Override
	public void endTag() {
		setSkipExecute(false);
	}
	
}
