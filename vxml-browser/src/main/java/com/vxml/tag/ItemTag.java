package com.vxml.tag;

import org.w3c.dom.Node;

public class ItemTag extends AbstractTag {

	public ItemTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		try {
			String input = getNode().getFirstChild().getTextContent();
			String inputMapping = getNode().getChildNodes().item(1).getTextContent();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
