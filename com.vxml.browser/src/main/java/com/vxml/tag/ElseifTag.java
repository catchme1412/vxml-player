package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.core.browser.VxmlBrowser;

public class ElseifTag extends AbstractTag {

    private boolean isSkipBackup;

    public ElseifTag(Node node) {
        super(node);
    }

    @Override
    public void startTag() {
        isSkipBackup = isSkipExecute();

    }

    @Override
    public void execute() {
        Boolean isIfCondition = (Boolean) VxmlBrowser.getContext().executeScript(
                "_vxmlExecutionContext.ifConditionLevel_" + ifConditionLevel);
        if (!isIfCondition) {
            String cond = getAttribute("cond");
            Boolean elseIfCondition = (Boolean) VxmlBrowser.getContext().executeScript(cond);
            
            if (elseIfCondition != null && elseIfCondition) {
                setSkipExecute(false);
                // Just to skip else tag
                VxmlBrowser.getContext().executeScript("_vxmlExecutionContext.ifCondition=true");
            } else {
                setSkipExecute(true);
            }
        } else {
            setSkipExecute(true);
        }
    }

    @Override
    public void endTag() {
    }
}
