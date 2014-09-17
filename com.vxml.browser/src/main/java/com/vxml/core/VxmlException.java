package com.vxml.core;

import javax.script.ScriptException;


public class VxmlException extends RuntimeException {

	public VxmlException(Exception e) {
		super(e);
	}

	public VxmlException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

    public VxmlException(String string, ScriptException e) {
        super(string, e);
    }

}
