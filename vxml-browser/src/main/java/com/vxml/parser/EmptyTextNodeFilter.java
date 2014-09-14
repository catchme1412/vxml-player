package com.vxml.parser;

import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.traversal.NodeFilter;

public class EmptyTextNodeFilter implements NodeFilter {

	@Override
	public short acceptNode(Node n) {
		if (n.getNodeType() == Node.TEXT_NODE) {
			// Use trim() to strip off leading and trailing space.
			// If nothing is left, then reject the node
			if (((Text) n).getData().trim().length() == 0)
				return NodeFilter.FILTER_REJECT;
		}
		return NodeFilter.FILTER_ACCEPT;
	}

}
