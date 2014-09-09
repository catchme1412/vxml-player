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
		    //Not running as a thread
		    System.out.println("Audio Tag...." + converted);
		    
//		    new NativeCommand().play(converted);
//		    new AePlayWave(converted).run();
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
