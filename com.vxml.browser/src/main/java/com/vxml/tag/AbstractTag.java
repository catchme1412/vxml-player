package com.vxml.tag;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vxml.core.VxmlPlayer;

public abstract class AbstractTag implements Tag {

	private static Map<String, String> propertyMap = new HashMap<String, String>();

	protected static boolean isSkipTag;

	protected static int ifTagLevel;
	protected static int forEachTagCount;

	private static Map<String, Tag> tagRef = new HashMap<String, Tag>();

	private Node node;

	public AbstractTag(Node node) {
		this.setNode(node);
	}

	public void performTag() {
	    System.out.println(nodeToString(getNode()));
		Node previousTag = getNode().getPreviousSibling();
		if (previousTag != null && "if".equals(previousTag.getNodeName())) {
			ifTagLevel--;
			if (ifTagLevel == 0) {
				isSkipTag = false;
			}
		}
		if (previousTag != null && "foreach".equals(previousTag.getNodeName())) {
			forEachTagCount--;
			if (forEachTagCount == 0) {
				isSkipTag = false;
			}
		}
		if (!isSkipTag || isLogicalTag()) {
			execute();
		}
	}

	private boolean isLogicalTag() {
		return "elseif".equals(getNode().getNodeName()) || "else".equals(getNode().getNodeName());
	}

	public boolean isCorrespondingIfConditionIsTrue() {
		Boolean isIfConditionTrue = false;

		try {
			isIfConditionTrue = (Boolean) executeScript("IF_CONDITION_LEVEL_" + ifTagLevel + ";");
			if (isIfConditionTrue) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Object executeScript(String script) {
		if (!script.endsWith(";")) {
			script = script + ";";
		}
		return VxmlPlayer.context.executeScript(script);
	}

	public Object executeScript(File file) {
		return VxmlPlayer.context.executeScript(file);
	}

	@Override
	public Node getNode() {
		return node;
	}

	public void setProperty(String property, String value) {
		propertyMap.put(property, value);
	}

	public String getAttribute(String key) {
		Node namedItem = getNode().getAttributes().getNamedItem(key);
		return namedItem != null ? namedItem.getNodeValue() : null;
	}

	public void storeTag(String id, Tag tag) {
		tagRef.put(id, tag);
	}

	public Tag retrieveTag(String id) {
		return tagRef.get(id);
	}

	public void executeChildNodes() {
		NodeList list = getNode().getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node n = list.item(i);
			Tag tag = TagHandlerFactory.getTag(n);
			// System.out.println(nodeToString(tag.getNode()));
			((AbstractTag) tag).performTag();
		}
	}

	@Override
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
					Tag tag = TagHandlerFactory.getTag(node);
					((AbstractTag) tag).performTag();
					executeChildTree(node);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return nodeToString(getNode());
	}

	public static String nodeToString(Node node) {
		StringWriter sw = new StringWriter();
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.setOutputProperty(OutputKeys.INDENT, "no");
			t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (TransformerException te) {
			System.err.println("nodeToString Transformer Exception");
		}
		return sw.toString();
	}

	public void setNode(Node node) {
		this.node = node;
	}

}
