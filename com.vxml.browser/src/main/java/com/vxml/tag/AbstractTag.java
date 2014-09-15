package com.vxml.tag;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class AbstractTag implements Tag {

	private Node node;
	
	private static boolean isSkipExecute;

	public AbstractTag(Node node) {
		this.node = node;
	}

	public void startTag() {

	}

	public void endTag() {
	}

	public Node getNode() {
		return node;
	}

	public String getAttribute(String key) {
		Node namedItem = getNode().getAttributes().getNamedItem(key);
		return namedItem != null ? namedItem.getNodeValue() : null;
	}
	
	
	public void executeChildTree(Node startNode) {
		if (startNode == null) {
			System.out.println("Nothing to print!!");
			return;
		}
		try {
			// Tag tag = TagHandlerFactory.getTag(startNode);
			// ((AbstractTag)tag).execute();

			NodeList nl = startNode.getChildNodes();
			if (nl != null) {
				for (int i = 0; i < nl.getLength(); i++) {
					Node node = nl.item(i);
					Tag tag = TagFactory.get(node);
					((AbstractTag) tag).tryExecute();
					executeChildTree(node);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	

	public String nodeToString() {
		StringWriter sw = new StringWriter();
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(getNode()), new StreamResult(sw));
		} catch (TransformerException te) {
			System.err.println("nodeToString Transformer Exception");
		}
		return sw.toString();
	}

	@Override
	public String toString() {
		String xml = nodeToString();
		String tag = xml.substring(0, xml.indexOf(">") + 1);
		if (tag.isEmpty()) {
			return "\t#text:" + getNode().getTextContent().trim();
		}
		return tag;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public void tryExecute() {
		if (isSkipExecute()) {
			System.out.println("SKIPPING:" + this);
		} else {
			execute();
		}
	}

	public static boolean isSkipExecute() {
		return isSkipExecute;
	}

	public static  void setSkipExecute(boolean isSkip) {
		AbstractTag.isSkipExecute = isSkip;
	}
}
