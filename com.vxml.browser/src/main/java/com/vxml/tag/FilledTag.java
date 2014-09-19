package com.vxml.tag;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vxml.browser.event.Event;
import com.vxml.core.browser.VxmlBrowser;
import com.vxml.dtmf.DtmfInput;
import com.vxml.utils.XmlUtils;

public class FilledTag extends AbstractTag {

	private NoinputTag noinputTag;
	private NomatchTag nomatchTag;
	
	public FilledTag(Node node) {
		super(node);
	}

	@Override
	public void startTag() {
		NodeList nodes = XmlUtils.getNodesByXPath(getNode(), ".//noinput");
		if(nodes != null && nodes.getLength() > 0) {
			Node t = nodes.item(0);
			noinputTag = (NoinputTag) TagFactory.get(t);
		} else {
			nodes = XmlUtils.getNodesByXPath(getNode(), ".//../noinput");
			if(nodes != null && nodes.getLength() > 0) {
				Node t = nodes.item(0);
				noinputTag = (NoinputTag) TagFactory.get(t);
			}
		}
		
		nodes = XmlUtils.getNodesByXPath(getNode(), ".//nomatch");
		if(nodes != null && nodes.getLength() > 0) {
			Node t = nodes.item(0);
			nomatchTag = (NomatchTag) TagFactory.get(t);
		} else {
			nodes = XmlUtils.getNodesByXPath(getNode(), ".//../nomatch");
			if(nodes != null && nodes.getLength() > 0) {
				Node t = nodes.item(0);
				nomatchTag = (NomatchTag) TagFactory.get(t);
			}
		}
	}
	
	@Override
	public void execute() throws Event {
		try {
			String value = new DtmfInput().readWithTimeOut(5000);
			if (value != null) {
				String fieldName = getFieldName();
//				if (value.matches("-?\\d+(\\.\\d+)?")) {
//					VxmlBrowser.getContext().executeScript("var " + fieldName + "=" + value + ";");
//				} else {
//					VxmlBrowser.getContext().executeScript("var " + fieldName + "='" + value + "';");
//				}
				VxmlBrowser.getContext().assignScriptVar(fieldName, value);

			} else {
				// no input
				VxmlBrowser.getContext().getEventHandler().fireEvent("noinput");
				if (noinputTag != null) {
				    VxmlBrowser.getContext().getEventHandler().clearEvent();
					executeChildTree(noinputTag.getNode());
				} else {
				    VxmlBrowser.getContext().getEventHandler().clearEvent();
				}
//				AbstractTag.setSkipExecute(true);
			}
			// else {
			// Node sibling = getNode().getNextSibling();
			// while (!(sibling.getNodeName().equals("nomatch") ||
			// sibling.getNodeName().equals("noinput"))) {
			// sibling = sibling.getNextSibling();
			// }
			// executeChildTree(sibling);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getFieldName() {
		return getNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
	}

}
