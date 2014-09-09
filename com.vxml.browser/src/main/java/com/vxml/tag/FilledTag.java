package com.vxml.tag;

import org.w3c.dom.Node;

public class FilledTag extends AbstractTag {

	public FilledTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		try {
			String value = new DtmfInput().read();
			String fieldName = getFieldName();
			executeScript("var " + fieldName + "=" + value + ";");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getFieldName() {
		return getNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
	}


}
