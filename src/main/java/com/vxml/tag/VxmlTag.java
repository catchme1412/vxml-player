package com.vxml.tag;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import com.vxml.browser.parser.VxmlDoc;



public class VxmlTag extends Tag {

	
	public VxmlTag(Node node) {
		super(node);
	}

	private static List<String> appRootList = new ArrayList<String>();

	public void startTag() {
		String application = getAttribute("application");
		if (application != null && !appRootList.contains(application)) {
			appRootList.add(application);
			new VxmlDoc(application).play();
		}
	}
}
