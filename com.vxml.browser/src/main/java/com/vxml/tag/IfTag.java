package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.core.browser.VxmlBrowser;

public class IfTag extends AbstractTag {

    private boolean isIfConditionTrue;
    
	public IfTag(Node node) {
		super(node);
	}

	@Override
	public void startTag() {
	}
	
	@Override
	public void execute() {
	    String cond = getAttribute("cond");
        isIfConditionTrue = (Boolean) VxmlBrowser.getContext().executeScript(cond + ";");
        setSkipExecute(isIfConditionTrue);
        VxmlBrowser.getContext().executeScript("_vxmlExecutionContext.ifCondition=" + isIfConditionTrue);
	    if (isIfConditionTrue) {
	        setSkipExecute(false);
	    } else {
	        setSkipExecute(true);
	    }
	}
	
	@Override
	public void endTag() {
	}

}
