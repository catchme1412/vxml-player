package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.core.browser.VxmlBrowser;

public class VarTag extends AbstractTag {

    public VarTag(Node node) {
        super(node);
    }

    @Override
	public void execute() {
//		System.out.println(getAttribute("name"));
		String name = getAttribute("name");
        VxmlBrowser.getContext().executeScript("var " + name + ";");
		String value = getAttribute("expr");
		if (value != null) {
		    VxmlBrowser.getContext().executeScript(name+"=" +value+";");
		}
	}
}
