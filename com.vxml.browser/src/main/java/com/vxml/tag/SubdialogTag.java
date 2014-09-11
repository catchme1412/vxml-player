package com.vxml.tag;

import org.w3c.dom.Node;


public class SubdialogTag extends AbstractTag {


    public SubdialogTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
	    System.out.println("SUBDIALOG");
	}
	
	

}
