package com.vxml.tag;

import org.w3c.dom.Node;

public class AudioTag extends AbstractTag {

	public AudioTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		String source = getAttribute("src");
		try {
		    //Not running as a thread
		    //new AePlayWave(source).run();
		    System.out.println("Audio Tag...." + source);
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}


}
