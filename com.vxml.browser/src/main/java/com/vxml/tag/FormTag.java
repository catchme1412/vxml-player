package com.vxml.tag;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vxml.core.browser.VxmlBrowser;
import com.vxml.utils.XmlUtils;

public class FormTag extends AbstractTag {

	private NoinputTag noinputTag;

	public FormTag(Node node) {
		super(node);
	}

	@Override
	public void startTag() {
		
		String id = getAttribute("id");
		if (id != null) {
			VxmlBrowser.getContext().storeTag(id, this);
		}
	
		NodeList nodes = XmlUtils.getNodesByXPath(getNode(), ".//noinput");
		if(nodes != null && nodes.getLength() > 0) {
			Node t = nodes.item(0);
			noinputTag = (NoinputTag) TagFactory.get(t);
		}
	}

	@Override
	public void execute() {

	}

}
