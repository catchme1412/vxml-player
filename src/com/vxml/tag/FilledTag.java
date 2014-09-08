package com.vxml.tag;

import java.io.IOException;
import java.util.Scanner;

import org.w3c.dom.Node;

public class FilledTag extends AbstractTag {

	public FilledTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		try {
			Scanner in = new Scanner(System.in);
			String value = in.next();
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
