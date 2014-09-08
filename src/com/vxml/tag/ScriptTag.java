package com.vxml.tag;

import java.io.IOException;
import java.util.Scanner;

import org.w3c.dom.Node;

public class ScriptTag extends AbstractTag {

	public ScriptTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		try {
			Scanner in = new Scanner(System.in);
			String value = in.next();
			System.out.println(value);
			String fieldName = getFieldName();
			executeScript("var " + fieldName + "=" + value + ";");
			System.out.println(">>>>>>>>>>>>>>"+executeScript(fieldName +";"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getFieldName() {
		return getNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
	}


}
