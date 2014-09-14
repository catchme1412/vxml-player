package com.vxml.core.browser;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Stack;

import javax.script.ScriptException;

import com.vxml.core.VxmlException;
import com.vxml.tag.Tag;

public class VxmlExecutionContext {

	private Stack<Tag> stack;

	private ScriptExecutionContext scriptExecutionContext;
	private static boolean isSkipTagExecute;
	private static String docBaseUrl = "http://localhost:8080/javscript/";

	public VxmlExecutionContext() throws ScriptException {
		stack = new Stack<Tag>();
		scriptExecutionContext = new ScriptExecutionContext();
	}


	public boolean isSkipTagExecute() {
		return isSkipTagExecute;
	}

	public void setSkipTagExecute(boolean isSkipTagExecute) {
		this.isSkipTagExecute = isSkipTagExecute;
	}

	public Tag push(Tag tag) {
		return stack.push(tag);
	}

	public Tag pop() {
		return stack.pop();
	}

	public Object executeScript(String script) {
		try {
			return scriptExecutionContext.executeScript(script);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String getDocBaseUrl() {
		return docBaseUrl;
	}


	public static void setDocBaseUrl(String docBaseUrl) {
		VxmlExecutionContext.docBaseUrl = docBaseUrl;
	}
	
	public URI getFullUri(String uri) {
		URI u = null;
		try {
			u = new URI(uri);
			if (u.getScheme() == null) {
				u = new URI(docBaseUrl + uri);
			}
		} catch (URISyntaxException e) {
			throw new VxmlException(e);
		}
		return u;
		
	}

}
