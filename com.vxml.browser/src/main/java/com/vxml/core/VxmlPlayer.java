package com.vxml.core;

import com.vxml.parser.VxmlDoc;


public class VxmlPlayer {

	private String entryUrl;
    public static VxmlContext context = new VxmlContext() ;
	
	public VxmlPlayer(String entryPointUrl) {
		entryUrl = entryPointUrl;
	}

	public void start() throws Exception {
		VxmlDoc doc = new VxmlDoc(entryUrl);
		doc.play();
	}

	public static void main(String[] args) throws Exception {
		VxmlPlayer vxmlPlayer = new VxmlPlayer("http://localhost:8080/javascript/index.html");
		vxmlPlayer.start();
	}

}
