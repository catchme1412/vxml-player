package com.vxml.tag;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vxml.utils.XmlUtils;

public abstract class AbstractTag implements Tag {

	private Node node;

	private static boolean isSkipExecute;
	
	private String subdialogName;

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

	//similar to walk
	public void executeChildTree(Node startNode) {
        if (node.getNodeType() == Node.COMMENT_NODE
                || (node.getNodeType() == Node.TEXT_NODE && node.getTextContent().trim().isEmpty())) {
            return;
        }

        // recurse
        for (Node child = startNode.getFirstChild(); child != null; child = child.getNextSibling()) {
        	Tag tag = TagFactory.get(child);
//        System.out.println("START:" + node.getNodeType() + "::" + tag);
//        stack.add(tag);
        	tag.startTag();
        	((AbstractTag) tag).tryExecute();
        	executeChildTree(child);
			tag.endTag();
        }

//        System.out.println("END:" + tag);
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

    public String getSubdialogName() {
        return subdialogName;
    }

    public void setSubdialogName(String subdialogName) {
        this.subdialogName = subdialogName;
    }
}
