package com.vxml.tag;

import org.w3c.dom.Node;

public class GotoTag extends AbstractTag {

    public GotoTag(Node node) {
        super(node);
    }

    @Override
    public void execute() {
        if (!(getNode().getParentNode().getNodeName().equals("nomatch")
                || getNode().getParentNode().getNodeName().equals("noinput") || getNode().getParentNode()
                .getNodeName().equals("catch"))) {
            String src = getAttribute("src");
            String next = getAttribute("next");
            String target = src != null ? src : next;
            if (target.startsWith("#")) {
                Tag form = retrieveTag(target.substring(1));
                form.executeChildNodes();
            } else {
                new VxmlDoc(target).play();
            }
        }
    }

}
