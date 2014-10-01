package com.vxml.browser;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VxmlBrowserTest {
	private VxmlBrowser browserInstance;

	@BeforeTest
	private void init() {
		browserInstance = new VxmlBrowser();
	}

//	@Test
//	public void scriptEngineRetension() {
//		VxmlBrowser instance = new VxmlBrowser();
//		instance.setVar("v1", "'10'");
//		instance = new VxmlBrowser();
//		Object value = instance.getVar("v1");
//		// the script engine keeps the state even after creating a new instance
//		// of the browser
//		Assert.assertEquals("10", value);
//	}
	
	@Test
	public void test() {
		VxmlTestRunner runner = new VxmlTestRunner(browserInstance);
		runner.setEntryPointUrl("http://localhost:8080/vxmlBrowser/helloWorld.vxml");
//		runner.runTillNextInput();
//		runner.setNextInput("2");
		runner.runTillNextOutput();
		String output = runner.nextOutput();
	}
}
