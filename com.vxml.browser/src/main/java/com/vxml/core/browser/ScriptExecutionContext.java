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
        engine.eval("var application={};");
        engine.eval("application.ANI=2108925595;");
        engine.eval("application.UUID='1ECA9F03DD8E11E28505B0FAEB421300';");
        engine.eval("var session={};session.telephone={};session.telephone.dnis=8665390991;");
        engine.eval("var _vxmlExecutionContext={};");
        engine.eval("var event;");
    }

    public Object executeScript(String script) throws ScriptException {
        if (!script.endsWith(";")) {
            script += ";";
        }
        return engine.eval(script);
    }

    public void put(String key, Object val) {
        engine.put(key, val);
    }
}
