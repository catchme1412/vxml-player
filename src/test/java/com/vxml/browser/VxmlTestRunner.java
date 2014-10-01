package com.vxml.browser;

public class VxmlTestRunner {

	private VxmlBrowser browser;

	public VxmlTestRunner(VxmlBrowser browserInstance) {
		this.browser = browserInstance;
	}

	public void runTillNextInput() {
		
	}

	public void setNextInput(String dtmfInput) {
		
	}

	public void runTillNextOutput() {
		browser.runTillNextOutput();
		
	}

	public String nextOutput() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setEntryPointUrl(String entryPointUrl) {
		browser.setEntryPointUrl(entryPointUrl);
	}


}
