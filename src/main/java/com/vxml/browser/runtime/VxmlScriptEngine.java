package com.vxml.browser.runtime;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class VxmlScriptEngine {

	private ScriptEngine scriptEngine;
	
	public VxmlScriptEngine() {
		ScriptEngineManager manager = new ScriptEngineManager();
		scriptEngine = manager.getEngineByName("js");
	}
	
	private Object eval(String script) {
		try {
			return scriptEngine.eval(script + ";");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void assignVar(String varName, String expr) {
		setVar(varName, expr);
	}

	private void setVar(String varName, String expr) {
		Object evalResult = eval(expr);
		evalResult = evalResult != null ? evalResult : expr;
		scriptEngine.put(varName, evalResult);
	}
	
	public void initVar(String varName, String expr) {
		setVar(varName, expr);
	}
	
	public Object getVar(String varName) {
		return scriptEngine.get(varName);
	}
	
	public boolean isDefined(String varName) {
		return (Boolean) eval ("typeof " +varName +"=== 'undefined'");
	}
}
