package com.vxml.parser;

import java.net.URI;
import java.util.Stack;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

import com.vxml.core.browser.VxmlBrowser;
import com.vxml.store.DocumentStore;
import com.vxml.tag.AbstractTag;
import com.vxml.tag.Tag;
import com.vxml.tag.TagFactory;

public class VxmlDoc {

	private Document xmlDoc;
	private Stack<Tag> stack;
	private DocumentStore documentStore;

	public VxmlDoc(Document xml) {
		xmlDoc = xml;
		stack = new Stack<Tag>();
	}

	public VxmlDoc(String string) {
		URI uri;
		uri = VxmlBrowser.getContext().getFullUri(string);
		documentStore = new DocumentStore();
		xmlDoc = documentStore.getDoc(uri);

	}

	public Document getXmlDoc() {
		return xmlDoc;
	}

	private void walk(Node node) {
		System.out.println("START:" + node);
		Tag tag = TagFactory.get(node);
		stack.add(tag);
		tag.startTag();
		((AbstractTag) tag).tryExecute();
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

	public void play() {
		NodeIterator ni = ((DocumentTraversal) xmlDoc).createNodeIterator(xmlDoc.getDocumentElement(),
				NodeFilter.SHOW_ALL, new EmptyTextNodeFilter(), true);
		Node node;
		walk(ni.nextNode());
		// while ((node = ni.nextNode()) != null) {
		// Tag tag = TagFactory.get(node);
		// // ((AbstractTag) tag).performTag();
		// }
		//
		// NodeList nodes = doc.getChildNodes();
		// for (int i = 0; i < nodes.getLength(); i++) {
		// Tag vxmlTag = TagHandlerFactory.getTag(nodes.item(i));
		// vxmlTag.execute();
		// }
	}

}
