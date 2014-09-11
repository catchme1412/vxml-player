package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.tts.NativeCommand;

public class AudioTag extends AbstractTag {

	public AudioTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
	    String src = getAttribute("src");
	    String expr = getAttribute("expr");
	    String converted = src != null ? src : (String)executeScript(expr +";");
	    
		try {
		    System.out.println("Audio:" + converted);
		    new NativeCommand().play(converted);
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
