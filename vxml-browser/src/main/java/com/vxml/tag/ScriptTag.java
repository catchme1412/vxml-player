package com.vxml.tag;

import java.net.URI;

import org.w3c.dom.Node;

import com.vxml.core.browser.VxmlBrowser;
import com.vxml.store.DocumentStore;

public class ScriptTag extends AbstractTag {

	public ScriptTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
//	    System.out.println(AbstractTag.nodeToString(getNode()));
	    String src = getAttribute("src");
	    if (src != null) {
	        URI uri = VxmlBrowser.getContext().getFullUri(src);
	        StringBuilder script = new DocumentStore().getData(uri);
	        VxmlBrowser.getContext().executeScript(script.toString());
//	        System.out.println("MMMMMMMMMMMMMM"+ VxmlBrowser.getContext().executeScript("history_stack;"));
	    } else {
	        VxmlBrowser.getContext().executeScript(getNode().getTextContent());
	    }
	}

	private String getFieldName() {
		return getNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
	}

}
