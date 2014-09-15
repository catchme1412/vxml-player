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
	    String converted = src != null ? src : (String)VxmlBrowser.getContext().executeScript(expr +";");
	    
		try {
		    System.out.println("Audio:" + converted);
		    try {
//		        new NativeCommand().play(converted);
		        VxmlExecutionContext.setTtsAllowed(false);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	@Override
	public void endTag() {
	    VxmlExecutionContext.setTtsAllowed(true);
	}
}
