package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.browser.event.Event;
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

    // similar to walk
    public void executeChildTree(Node startNode) throws Event {
        if (node.getNodeType() == Node.COMMENT_NODE
                || (node.getNodeType() == Node.TEXT_NODE && node.getTextContent().trim().isEmpty())) {
            return;
        }

        // recurse
        for (Node child = startNode.getFirstChild(); child != null; child = child.getNextSibling()) {
            AbstractTag tag = (AbstractTag) TagFactory.get(child);
            if (tag.getNode().getNodeType() == Node.COMMENT_NODE
                    || (tag.getNode().getNodeType() == Node.TEXT_NODE && tag.getNode().getTextContent().trim()
                            .isEmpty())) {
                continue;
            }
            // System.out.println("START:" + node.getNodeType() + "::" + tag);
            // stack.add(tag);
            tag.startTag();
            ((AbstractTag) tag).tryExecute();
            executeChildTree(child);
            tag.endTag();
        }

        // System.out.println("END:" + tag);
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

    public void tryExecute() throws Event {
        if (isSkipExecute()) {
            // System.out.println("SKIPPING:" + this);
        } else {
            System.out.println("EXECUTING:" + this + "\t<=\t" + getNode().getOwnerDocument().getDocumentURI());
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
