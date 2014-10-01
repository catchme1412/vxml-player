package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.browser.parser.XmlUtils;
import com.vxml.browser.runtime.VxmlExecutionEnvironment;
import com.vxml.browser.runtime.VxmlScriptEngine;

public abstract class Tag {

	
	private Node node;

	private static boolean isSkipTagExecution;
	
	private static VxmlExecutionEnvironment vmlExecutionEnvironment;
	private static VxmlScriptEngine scriptEngine;

	public Tag(Node node) {
		this.node = node;
		if(vmlExecutionEnvironment == null) {
			vmlExecutionEnvironment = new VxmlExecutionEnvironment();
		}
		if (scriptEngine == null) {
			// TODO add lock for synchronization
			scriptEngine = new VxmlScriptEngine();
		}
	}

	public static boolean isSkipTagExecution() {
		return isSkipTagExecution;
	}

	public static void setSkipTagExecution(boolean isSkip) {
		isSkipTagExecution = isSkip;
	}

	public void startTag() {

	}

	public void execute() {

	}

	public void endTag() {

	}

	public String getTextContent() {
		return node.getTextContent();
	}
	public String getAttribute(String key) {
		return XmlUtils.getAttribute(node, key);
	}
	
	public void playTTS(String text) {
		vmlExecutionEnvironment.playTTS(text);
	}

	@Override
	public String toString() {
		String xml = nodeToString();
		String tag = xml.substring(0, xml.indexOf(">") + 1);
		if (tag.isEmpty()) {
			return "\t#text:" + node.getTextContent().trim();
		}
		return tag;
	}

	private String nodeToString() {
		return XmlUtils.nodeToString(node);
	}

}
