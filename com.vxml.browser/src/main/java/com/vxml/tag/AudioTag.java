package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.core.browser.VxmlBrowser;
import com.vxml.core.browser.VxmlExecutionContext;
import com.vxml.tts.NativeCommand;

public class AudioTag extends AbstractTag {

    public AudioTag(Node node) {
        super(node);
    }

    @Override
    public void execute() {
        String src = getAttribute("src");
        String expr = getAttribute("expr");

        try {
            Object convert = null;
            if (expr != null) {
                convert = VxmlBrowser.getContext().executeScript(expr);
            }
            String converted = (String) (src != null ? src : convert);
            if (converted != null) {
                System.out.println("Audio:" + converted);
                try {
                    converted = converted.replaceAll("audio.en-US.tellme.com", "ivraudio.orbitz.net");
                    new NativeCommand().play(converted);
                    VxmlExecutionContext.setTtsAllowed(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endTag() {
        VxmlExecutionContext.setTtsAllowed(true);
    }
}
