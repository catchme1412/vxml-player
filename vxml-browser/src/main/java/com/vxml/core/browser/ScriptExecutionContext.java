package com.vxml.core.browser;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptExecutionContext {

	private ScriptEngineManager manager;
    private ScriptEngine engine;
    
    public ScriptExecutionContext() throws ScriptException {
    	manager = new ScriptEngineManager();
        engine = manager.getEngineByName("js");
        engine.eval("var _vxmlExecutionContext={};");
    }
    
    public Object executeScript(String script) throws ScriptException {
    	if (!script.endsWith(";")) {
    		script += ";";
    	}
    	return engine.eval(script);
    }
}
