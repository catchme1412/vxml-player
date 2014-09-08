package com.vxml.tag;

import org.w3c.dom.Node;

public class GotoTag extends AbstractTag {

	public GotoTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String src = getAttribute("src");
		new VxmlDoc(src).play();
		
	}

	
}
