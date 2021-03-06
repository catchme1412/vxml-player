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
        String expr = getAttribute("expr");
        //parentNodeName.equals("nomatch") || parentNodeName.equals("noinput") || parentNodeName.equals("catch")
//        if (!(isSkipTag)) {
//            String target = src != null ? src : next;
//            target = (String) (target != null ? target : executeScript(expr));
//            if (target.startsWith("#")) {
//                Tag form = retrieveTag(target.substring(1));
//                executeChildTree(form.getNode());
//            } else {
//                new VxmlDoc(target).play();
//            }
//        }
//        isSkipTag = false;
    }

}
