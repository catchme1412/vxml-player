package com.vxml.browser;

import org.testng.Assert;
import org.testng.annotations.*;

import com.vxml.core.browser.VxmlBrowser;

public class VxmlBrowserTest {

	private VxmlBrowser vxmlBrowser;

	@BeforeMethod
	public void init() {
		vxmlBrowser = new VxmlBrowser();
	}

	@Test
	public void f() {
		vxmlBrowser.setEntryPointUrl("");
		VxmlBrowserVerifier verifier = new VxmlBrowserVerifier(vxmlBrowser);
		verifier.start();
		Assert.assertEquals(verifier.nextAudioUrl(), "");
		verifier.inputDtmf(2);
		Assert.assertEquals(verifier.nextTTS(), "");
		Assert.assertEquals(verifier.isDisconnected(),true);
	}
}
