package com.vxml.tag;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public abstract class AbstractTag implements Tag {

	private static VxmlContext context = new VxmlContext() ;
	private static Map propertyMap = new HashMap();
	
	private Node node;
	
	public AbstractTag(Node node) {
		this.node = node;
	}

	public void performTag() {
//		if(node.getNodeType() == Node.ELEMENT_NODE) {
//			System.out.println("Running:" + node.getNodeName());
//			System.out.println(node);
			execute();
//		}
	}
	
	public Object executeScript(String script) {
		return context.executeScript(script);
	}
	
	@Override
	public Node getNode() {
		return node;
	}
	
	public void setProperty(String property, String value) {
		propertyMap.put(property, value);
	}
	
	public String getAttribute(String key) {
		Node namedItem = node.getAttributes().getNamedItem(key);
		return namedItem != null ? namedItem.getNodeValue() : null;
	}

}
