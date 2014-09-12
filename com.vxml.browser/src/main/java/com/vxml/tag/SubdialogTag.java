package com.vxml.tag;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SubdialogTag extends AbstractTag {

	public SubdialogTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		String srcexpr = getAttribute("srcexpr");
		String src = getAttribute("src");
		String target = getAttribute("name");
		src = src != null ? src : (String)executeScript(srcexpr);
		
		StringBuilder url = new StringBuilder();
		url.append(VxmlPlayer.context.getDocBase());
		url.append(src);
		url.append("?");
		NodeList paramList = getNode().getChildNodes();
		for (int i= 0; i < paramList.getLength(); i++) {
			Node node = paramList.item(i);
			if ("param".equals(node.getNodeName())) {
				String name = node.getAttributes().getNamedItem("name").getNodeValue();
				String expr = node.getAttributes().getNamedItem("expr").getNodeValue();
				url.append(name);
				url.append("=");
				url.append(executeScript(expr));
				url.append("&");
			}
		}
		
		StringBuilder r = new DocumentStore().getData(url.toString());
		executeScript("var " + target +"=" + r.toString());
		
		
	}

	
}
