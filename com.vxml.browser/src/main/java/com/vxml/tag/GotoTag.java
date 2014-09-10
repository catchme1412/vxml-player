package com.vxml.tag;

import org.w3c.dom.Node;

public class GotoTag extends AbstractTag {

    public GotoTag(Node node) {
        super(node);
    }

    @Override
    public void execute() {
        String parentNodeName = getNode().getParentNode().getNodeName();
        String src = getAttribute("src");
        String next = getAttribute("next");
        if (next.contains("greeting_orbitz.vxml")) {
            System.out.println("HOOOOOOOOOOOO");
        }
        if (!(parentNodeName.equals("nomatch") || parentNodeName.equals("noinput") || parentNodeName.equals("catch"))) {
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
