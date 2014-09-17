package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.browser.event.ReturnFromSubdialogEvent;
import com.vxml.core.browser.ScriptExecutionContext;
import com.vxml.core.browser.VxmlBrowser;

public class ReturnTag extends AbstractTag {

    public ReturnTag(Node node) {
        super(node);
    }

    @Override
    public void startTag() {
        // if (null == getSubdialogName()) {
        // throw new VxmlException("<return> expected inside <subdialog> only:"
        // + this.getNode().getOwnerDocument().getDocumentURI());
        // }
    }

    @Override
    public void execute() throws ReturnFromSubdialogEvent {
        String namelist = getAttribute("namelist");
        String subdialogName = (String) VxmlBrowser.getContext().executeScript(
                ScriptExecutionContext.SCRIPT_EXECUTION_NAME_SPACE + ".subdialogName");
        for (String name : namelist.split(" ")) {

            String subDialogVariableName = subdialogName + "." + name;
            VxmlBrowser.getContext().executeScript(subDialogVariableName +"={}");
            VxmlBrowser.getContext().assignScriptVar(subDialogVariableName , VxmlBrowser.getContext().executeScript(name));
        }
        throw new ReturnFromSubdialogEvent();
    }

    @Override
    public void endTag() {
    }

}
