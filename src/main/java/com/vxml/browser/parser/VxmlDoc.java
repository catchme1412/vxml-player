package com.vxml.browser.parser;

import java.net.URISyntaxException;

import org.w3c.dom.Document;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

import com.vxml.store.DocumentStore;

public class VxmlDoc {

	private Document xmlDoc;

	public VxmlDoc(Document xmlDoc) {
		this.xmlDoc = xmlDoc;
	}

	public VxmlDoc(String uri) {
		try {
			xmlDoc = new DocumentStore().getDoc(uri);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public Document getXmlDoc() {
		return xmlDoc;
	}

	public NodeIterator createNodeIterator() {
		return ((DocumentTraversal) xmlDoc).createNodeIterator(xmlDoc.getDocumentElement(), NodeFilter.SHOW_ALL
				& ~NodeFilter.SHOW_COMMENT, new EmptyTextNodeFilter(), true);
	}

	public void play() {
		new VxmlParser().parse(this);
	}

}
