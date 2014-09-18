package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.core.browser.VxmlBrowser;

public class IfTag extends AbstractTag {

    private Boolean isIfConditionTrue;
    private boolean isSkipBackup;
    
	public IfTag(Node node) {
		super(node);
	}

	@Override
	public void startTag() {
	    isSkipBackup = isSkipExecute();
	}
	
	@Override
	public void execute() {
	    String cond = getAttribute("cond");
        isIfConditionTrue = (Boolean) VxmlBrowser.getContext().executeScript(cond);
        isIfConditionTrue = isIfConditionTrue != null ? isIfConditionTrue : false;
        VxmlBrowser.getContext().executeScript("_vxmlExecutionContext.ifCondition=" + isIfConditionTrue);
	    if (isIfConditionTrue) {
	        setSkipExecute(false);
	    } else {
	        setSkipExecute(true);
	    }
	}
	
	@Override
	public void endTag() {
	    setSkipExecute(isSkipBackup);
	}

}
