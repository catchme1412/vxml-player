package com.vxml.tag;


public class VxmlPlayer {

	private String entryUrl;
	
	public VxmlPlayer(String entryPointUrl) {
		entryUrl = entryPointUrl;
	}

	public void start() throws Exception {
		VxmlDoc doc = new VxmlDoc(entryUrl);
		doc.play();
	}
	
	public static void main(String[] args) throws Exception {
		new VxmlPlayer("http://localhost:8080/javascript/index.html").start();
	}

}
