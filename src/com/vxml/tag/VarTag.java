package com.vxml.tag;

import javax.script.ScriptException;

import org.w3c.dom.Node;

public class VarTag extends AbstractTag {

	public VarTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
//		System.out.println(getAttribute("name"));
		executeScript("var " + getAttribute("name"));

	}

}
