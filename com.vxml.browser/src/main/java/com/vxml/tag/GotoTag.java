package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.browser.event.Event;
import com.vxml.core.browser.VxmlBrowser;
import com.vxml.parser.VxmlDoc;

public class GotoTag extends AbstractTag {

    private String target;
    
    public GotoTag(Node node) {
        super(node);
    }

    @Override
    public void startTag() {
        String src = getAttribute("src");
        String next = getAttribute("next");
        String expr = getAttribute("expr");
        target = src != null ? src : next;
        target = (String) (target != null ? target : VxmlBrowser.getContext().executeScript(expr));
    }

    @Override
    public void execute() throws Event {
        if (target.startsWith("#")) {
            Tag form = VxmlBrowser.getContext().getTag(target.substring(1));
            ((AbstractTag)form).tryExecute();
        } else {
            new VxmlDoc(target).play();
        }
    }

}
