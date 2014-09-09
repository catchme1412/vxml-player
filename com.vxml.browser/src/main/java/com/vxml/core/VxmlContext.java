package com.vxml.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class VxmlContext {

	private ScriptEngineManager manager;
	private ScriptEngine engine;
	private String docBase = "http://localhost:8080/javascript/";
	
	
	public VxmlContext() {
	    manager = new ScriptEngineManager();
	    engine = manager.getEngineByName("js");
	    try {
            engine.eval("var application={};application.ANI='100'; application.UUID='1ECA9F03DD8E11E28505B0FAEB421300';");
            System.out.println(executeScript("application.UUID;"));
            engine.eval("var C1;var C2;var C3;var C4;var C5;var C6;var C7;var C8;var C9;var C10;");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
	}
	public Object executeScript(String script) {
		try {
			return engine.eval(script);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object executeScript(File file) {
	    try {
            return engine.eval(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
    public String getDocBase() {
        return docBase;
    }
    public void setDocBase(String docBase) {
        this.docBase = docBase;
    }
	
}
