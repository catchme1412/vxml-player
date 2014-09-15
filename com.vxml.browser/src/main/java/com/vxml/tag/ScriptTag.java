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
	    String src = getAttribute("src");
	    if (src != null) {
	        URI uri = VxmlBrowser.getContext().getFullUri(src);
	        StringBuilder script = new DocumentStore().getData(uri);
	        VxmlBrowser.getContext().executeScript(script.toString());
	    } else {
	        VxmlBrowser.getContext().executeScript(getNode().getTextContent());
	    }
	}


}
