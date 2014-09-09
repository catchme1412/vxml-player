package com.vxml.tag;

import java.io.IOException;

import org.w3c.dom.Node;

import com.vxml.tts.NativeCommand;

public class TextTag extends AbstractTag {

    public TextTag(Node node) {
        super(node);
    }

    @Override
    public void execute() {
        String text = getNode().getTextContent().trim();
        if (!text.isEmpty()) {
            System.out.println("TEXT >>>>>>" + text + " ::" + getNode().getParentNode().getNodeName());
        }

        if (!text.isEmpty() && getNode().getParentNode().getNodeName().equals("prompt")) {
            System.out.println(text);
            try {
                new NativeCommand().speak(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
