package com.vxml.tag;

import java.net.URI;
import java.net.URISyntaxException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vxml.core.browser.VxmlBrowser;
import com.vxml.core.browser.VxmlExecutionContext;
import com.vxml.store.DocumentStore;

public class SubdialogTag extends AbstractTag {

	public SubdialogTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		String srcexpr = getAttribute("srcexpr");
		String src = getAttribute("src");
		String target = getAttribute("name");
		src = src != null ? src : (String)VxmlBrowser.getContext().executeScript(srcexpr);
		
		StringBuilder url = new StringBuilder();
        url.append(VxmlExecutionContext.getDocBaseUrl());
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
				url.append(VxmlBrowser.getContext().executeScript(expr));
				url.append("&");
			}
		}
		
		StringBuilder r = null;
        try {
            r = new DocumentStore().getData(new URI(url.toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("FFf"+ r);
		VxmlBrowser.getContext().executeScript("var " + target +"=<![CDATA[" + r +"]]>");
		
		
	}

	
}
