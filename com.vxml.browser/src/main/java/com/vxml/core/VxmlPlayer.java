package com.vxml.core;

import com.vxml.tag.VxmlDoc;


public class VxmlPlayer {

	private String entryUrl;
    public static VxmlContext context = new VxmlContext() ;
	
	public VxmlPlayer(String entryPointUrl) {
		entryUrl = entryPointUrl;
	}

	public void start() throws Exception {
		try {
			VxmlDoc doc = new VxmlDoc(entryUrl);
			doc.play();
		} finally {
//			DocumentStore.close();
		}
	}

	public static void main(String[] args) throws Exception {
		new VxmlPlayer("http://localhost:8080/javascript/index.html").start();
	}

}
