package com.vxml.core.browser;

import java.net.URI;
import java.net.URISyntaxException;

import javax.script.ScriptException;

import org.w3c.dom.Document;

import com.vxml.core.VxmlException;
import com.vxml.parser.VxmlDoc;
import com.vxml.parser.VxmlParser;
import com.vxml.store.DocumentStore;

public class VxmlBrowser {

	private String entryPointUrl;
	private static VxmlExecutionContext context;
	public VxmlBrowser() {
		if (context != null) {
			try {
				context = new VxmlExecutionContext();
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void start() throws VxmlException, URISyntaxException {
		Document xml = new DocumentStore().getDoc(new URI(entryPointUrl));
		VxmlDoc vxmlDoc = new VxmlDoc(xml);
		new VxmlParser().parse(vxmlDoc);;
	}

	public String getEntryPointUrl() {
		return entryPointUrl;
	}

	public void setEntryPointUrl(String entryPointUrl) {
		this.entryPointUrl = entryPointUrl;
	}
	
	public static void main(String[] args) throws VxmlException, URISyntaxException {
		VxmlBrowser vxmlBrowser = new VxmlBrowser();
		vxmlBrowser.setEntryPointUrl("http://localhost:8080/javascript/index.html");
		vxmlBrowser.start();
	}

	public static VxmlExecutionContext getContext() {
		return context;
	}

	public void setContext(VxmlExecutionContext context) {
		this.context = context;
	}
}
