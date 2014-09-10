package com.vxml.tag;

import java.net.URI;
import java.net.URISyntaxException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

import com.vxml.core.VxmlPlayer;

public class VxmlDoc {

	private Document doc;
	private DocumentStore documentStore;
	public VxmlDoc(Document doc) {
		this.doc = doc;
	}

	public VxmlDoc(String uriLink) {
		URI uri;
		try {
			uri = getFullUri(uriLink);

			documentStore = new DocumentStore();
			this.doc = documentStore.getDoc(uri);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

    private URI getFullUri(String uriLink) throws URISyntaxException {
        URI uri;
        uri = new URI(uriLink);
        if (uri.getScheme() == null) {
        	uri = new URI(VxmlPlayer.context.getDocBase() + uriLink);
        }
        return uri;
    }

	public void play() {
		NodeIterator ni = ((DocumentTraversal) doc).createNodeIterator(
				doc.getDocumentElement(), NodeFilter.SHOW_ALL, new EmptyTextNodeFilter(), true);
		Node node;
		while ((node = ni.nextNode()) != null) {
			Tag tag = TagHandlerFactory.getTag(node);
			((AbstractTag) tag).performTag();
		}
		//
		// NodeList nodes = doc.getChildNodes();
		// for (int i = 0; i < nodes.getLength(); i++) {
		// Tag vxmlTag = TagHandlerFactory.getTag(nodes.item(i));
		// vxmlTag.execute();
		// }
	}
}
