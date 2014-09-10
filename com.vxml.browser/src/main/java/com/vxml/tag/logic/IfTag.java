package com.vxml.tag.logic;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vxml.tag.AbstractTag;
import com.vxml.tag.Tag;
import com.vxml.tag.TagHandlerFactory;

public class IfTag extends AbstractTag {

    public IfTag(Node node) {
        super(node);
    }

    @Override
    public void execute() {
        ifTagCount++;
        String cond = getAttribute("cond");
        Boolean isIfConditionTrue = (Boolean) executeScript(cond + ";");
        NodeList children = getNode().getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (isIfConditionTrue) {
//                if (!isBlockIndicator(node)) {
//                    Tag tag = TagHandlerFactory.getTag(node);
//                    ((AbstractTag) tag).performTag();
//                }
                return;
            } else if (isBlockIndicator(node)) {
                Tag tag = TagHandlerFactory.getTag(node);
                if (tag instanceof ElseTag) {
                    if (!isIfConditionTrue) {
                        ((AbstractTag) tag).performTag();
                    }
                } else {
                    ((AbstractTag) tag).performTag();
                }
                
            }
        }
        // NodeIterator ni = ((DocumentTraversal)
        // getNode()).createNodeIterator(getNode(), NodeFilter.SHOW_ALL,
        // new EmptyTextNodeFilter(), true);
        // Node node;
        // while ((node = ni.nextNode()) != null) {
        // if (isIfConditionTrue) {
        // if (!isBlockIndicator(node)) {
        // Tag tag = TagHandlerFactory.getTag(node);
        // ((AbstractTag)tag).performTag();
        // }
        // } else if (isBlockIndicator(node)) {
        // Tag tag = TagHandlerFactory.getTag(node);
        // ((AbstractTag)tag).executeChildNodes();
        // }
        // }
        // if (isIfConditionTrue) {
        // NodeIterator ni = ((DocumentTraversal)
        // getNode()).createNodeIterator(getNode(), NodeFilter.SHOW_ALL,
        // new EmptyTextNodeFilter(), true);
        // Node node;
        // while ((node = ni.nextNode()) != null) {
        // if (!isBlockIndicator(node)) {
        // Tag tag = TagHandlerFactory.getTag(node);
        // ((AbstractTag)tag).performTag();
        // }
        // }
        // // for (int i = 0; i < children.getLength(); i++) {
        // // Node n = children.item(i);
        // // if (!isBlockIndicator(n)) {
        // // Tag tag = TagHandlerFactory.getTag(n);
        // // tag.execute();
        // // }
        // // }
        // } else {
        //
        // }
    }

    private boolean isBlockIndicator(Node n) {
        if (n != null && "else".equals(n.getNodeName()) || "elseif".equals(n.getNodeName())) {
            return true;
        }
        return false;
    }

}
