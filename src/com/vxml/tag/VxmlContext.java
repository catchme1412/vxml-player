package com.vxml.tag;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class VxmlContext {

	public boolean insideFieldTag;
	
	private static final ScriptEngineManager manager = new ScriptEngineManager();
	private static ScriptEngine engine = manager.getEngineByName("js");
	
	public boolean isInsideFieldTag() {
		return insideFieldTag;
	}
	
	public Object executeScript(String script) {
		try {
			return engine.eval(script);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
