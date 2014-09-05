package com.vxml.tag;
import org.w3c.dom.Node;


public class FieldTag extends AbstractTag {


	public FieldTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		System.out.println(getAttribute("name"));
		super.execute();
	}

}
