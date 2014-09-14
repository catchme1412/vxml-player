package com.vxml.parser;

import java.util.Stack;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

import com.vxml.tag.AbstractTag;
import com.vxml.tag.Tag;
import com.vxml.tag.TagFactory;

public class VxmlParser {

	private Stack<Tag> stack;

	public VxmlParser() {
		stack = new Stack<Tag>();
	}

	public void executeChildTree(Node startNode) {
		if (startNode == null) {
			return;
		}
		try {
			// Tag tag = TagHandlerFactory.getTag(startNode);
			// ((AbstractTag)tag).execute();

			NodeList nl = startNode.getChildNodes();
			if (nl != null) {
				for (int i = 0; i < nl.getLength(); i++) {
					Node node = nl.item(i);
					// Tag tag = TagHandlerFactory.getTag(node);
					// ((AbstractTag) tag).performTag();
					System.out.println("START:" + node);
					executeChildTree(node);
					System.out.println("END OF:" + node);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void walk(Node node) {
		System.out.println("START:" + node);
		Tag tag = TagFactory.get(node);
		stack.add(tag);
		tag.startTag();
		((AbstractTag)tag).tryExecute();
		int type = node.getNodeType();

		// recurse
		for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
			walk(child);
		}

		// without this the ending tags will miss
		if (type == Node.ELEMENT_NODE) {
			System.out.println("END:" + node.getNodeName());
			Tag t = stack.pop();
			t.endTag();
		}

	}

	public void parse(VxmlDoc vxmlDoc) {
		Document doc = vxmlDoc.getXmlDoc();
		NodeIterator ni = ((DocumentTraversal) doc).createNodeIterator(doc.getDocumentElement(), NodeFilter.SHOW_ALL
				& ~NodeFilter.SHOW_COMMENT, new EmptyTextNodeFilter(), true);
		walk(ni.nextNode());
	}

}
