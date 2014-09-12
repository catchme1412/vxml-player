package com.vxml.tag;

import org.w3c.dom.Node;

public class FilledTag extends AbstractTag {

	public FilledTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		try {
			String value = new DtmfInput().readWithTimeOut();
			if (value != null) {
			    String fieldName = getFieldName();
			    executeScript("var " + fieldName + "=" + value + ";");
			} else {
			    Node sibling = getNode().getNextSibling();
			    while (!(sibling.getNodeName().equals("nomatch") || sibling.getNodeName().equals("noinput"))) {
			        sibling = sibling.getNextSibling();
			    }
			    executeChildTree(sibling);
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	private String getFieldName() {
		return getNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
	}


}
