package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.core.browser.VxmlBrowser;

public class ElseTag extends AbstractTag {

	public ElseTag(Node node) {
		super(node);
	}
	
	@Override
	public void startTag() {
	    Boolean isIfCondition = (Boolean) VxmlBrowser.getContext().executeScript("_vxmlExecutionContext.ifCondition");
	    if (isIfCondition) {
	        setSkipExecute(true);
	    } else {
	        setSkipExecute(false);
	    }
	}

	@Override
	public void execute() {
	    
	}
}
