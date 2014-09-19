package com.vxml.browser;

import org.testng.annotations.*;

import com.vxml.core.browser.VxmlBrowser;

public class VxmlBrowserTest {

	private VxmlBrowser vxmlBrowser;
	
	@BeforeMethod
	public void init() {
		vxmlBrowser = new  VxmlBrowser();
	}
	@Test
	public void f() {
		
	}
}
