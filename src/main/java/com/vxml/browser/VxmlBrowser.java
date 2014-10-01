package com.vxml.browser;

import java.net.URISyntaxException;

import com.vxml.browser.parser.VxmlDoc;

public class VxmlBrowser {
	private String entryPointUrl;
	
	public void start() throws URISyntaxException {
		new VxmlDoc(entryPointUrl).play();
	}
	
	
	public static void main(String[] args) throws URISyntaxException {
		String string = "http://localhost:8080/vxmlBrowser/helloWorld.vxml";
		VxmlBrowser browser = new VxmlBrowser();
		browser.setEntryPointUrl(string);
		browser.start();
	}


	public void setEntryPointUrl(String entryPointUrl) {
		this.entryPointUrl = entryPointUrl;
		
	}


	public void runTillNextOutput() {
		
	}

}