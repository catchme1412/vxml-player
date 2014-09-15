package com.vxml.tag;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vxml.utils.XmlUtils;

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
		return XmlUtils.getAttribute(getNode(), key);
	}

	public void executeChildTree(Node startNode) {
		if (startNode == null) {
			System.out.println("Nothing to print!!");
			return;
		}
		try {
			Tag tag = TagFactory.get(startNode);
			((AbstractTag) tag).execute();

			NodeList nl = startNode.getChildNodes();
			if (nl != null) {
				for (int i = 0; i < nl.getLength(); i++) {
					Node node = nl.item(i);
					tag = TagFactory.get(node);
					((AbstractTag) tag).tryExecute();
					executeChildTree(node);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public String nodeToString() {
		return XmlUtils.nodeToString(getNode());
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

	public static void setSkipExecute(boolean isSkip) {
		AbstractTag.isSkipExecute = isSkip;
	}
}
