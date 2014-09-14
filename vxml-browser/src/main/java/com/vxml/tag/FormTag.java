package com.vxml.tag;
import org.w3c.dom.Node;


public class FormTag extends AbstractTag {


	public FormTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
	    String id = getAttribute("id");
	    if(id != null) {
//	    	storeTag(id, this);
	    }
	}

}
