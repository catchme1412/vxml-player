package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.core.VxmlPlayer;

public class ScriptTag extends AbstractTag {

	public ScriptTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
//	    System.out.println(AbstractTag.nodeToString(getNode()));
	    String src = getAttribute("src");
	    if (src != null) {
	        src = VxmlPlayer.context.getDocBase() + src;
	        StringBuilder script = new UrlFetchService().fetch(src);
	        executeScript(script.toString());
//	        System.out.println("MMMMMMMMMMMMMM"+ executeScript("history_stack;"));
	    } else {
	        executeScript(getNode().getTextContent());
	    }
	}

	private String getFieldName() {
		return getNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
	}

}
