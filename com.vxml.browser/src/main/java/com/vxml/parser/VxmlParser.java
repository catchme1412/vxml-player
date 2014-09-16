package com.vxml.parser;

import java.util.Stack;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

import com.vxml.browser.event.Event;
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

    public void walk(Node node) throws Event {
        if (node.getNodeType() == Node.COMMENT_NODE
                || (node.getNodeType() == Node.TEXT_NODE && node.getTextContent().trim().isEmpty())) {
            return;
        }
        Tag tag = TagFactory.get(node);
//        System.out.println("START:" + node.getNodeType() + "::" + tag);
//        stack.add(tag);
        tag.startTag();
        ((AbstractTag) tag).tryExecute();

        // recurse
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            walk(child);
        }

//        System.out.println("END:" + tag);
        tag.endTag();
    }

    public void parse(VxmlDoc vxmlDoc) throws Event {
        Document doc = vxmlDoc.getXmlDoc();
        NodeIterator ni = ((DocumentTraversal) doc).createNodeIterator(doc.getDocumentElement(), NodeFilter.SHOW_ALL
                & ~NodeFilter.SHOW_COMMENT, new EmptyTextNodeFilter(), true);
        walk(ni.nextNode());
    }

}
