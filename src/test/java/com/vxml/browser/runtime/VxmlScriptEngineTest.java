package com.vxml.browser.runtime;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VxmlScriptEngineTest {

	private VxmlScriptEngine scriptEngine;

	@BeforeTest
	private void init() {
		scriptEngine = new VxmlScriptEngine();

	}

	@Test
	public void assignVar() {
		scriptEngine.assignVar("v3", "'true'");
		Object value = scriptEngine.getVar("v3");
		Assert.assertEquals("true", value);
		Assert.assertFalse(value instanceof Boolean);
	}

	@Test
	public void testIsUndefined() {
		Assert.assertTrue(scriptEngine.isDefined("noVarDefinedWithThisName"));
	}

	@Test
	public void initVarAndGetVar() {
		scriptEngine.initVar("v1", "'true'");
		Object value = scriptEngine.getVar("v1");
		Assert.assertEquals("true", value);
		scriptEngine.initVar("v2", "true");
		value = scriptEngine.getVar("v2");
		Assert.assertTrue((Boolean) value);
	}
	
	@Test
	public void initVar() {
		scriptEngine.initVar("v1", "'/someUrl.vxml'");
		Object value = scriptEngine.getVar("v1");
		Assert.assertEquals(value, "/someUrl.vxml");
	}
}
