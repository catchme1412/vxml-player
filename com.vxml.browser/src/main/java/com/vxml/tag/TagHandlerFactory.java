package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.tag.logic.ElseIfTag;
import com.vxml.tag.logic.ElseTag;
import com.vxml.tag.logic.IfTag;

public class TagHandlerFactory {

	public static Tag getTag(Node node) {
		// System.out.println(node.getNodeName() +
		// AbstractTag.nodeToString(node));
		// System.out.println(node.getNodeName());

		if (node.getNodeType() == Node.TEXT_NODE) {
			return new TextTag(node);
		}
		String nodeName = node.getNodeName();
//		try {
//			String name = "com.vxml.tag."+Character.toUpperCase( nodeName.charAt(0)) + nodeName.substring(1) + "Tag";
//			Class<?> forName = Class.forName(name);
//			Tag instance = (Tag)forName.newInstance();
//			((AbstractTag)instance).setNode(node);
//			return instance;
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if (nodeName.equals("vxml")) {
			return new VxmlTag(node);
		} else if (nodeName.equals("var")) {
			return new VarTag(node);
		} else if (nodeName.equals("form")) {
			return new FormTag(node);
		} else if (nodeName.equals("goto")) {
			return new GotoTag(node);
		} else if (nodeName.equals("prompt")) {
			return new PromptTag(node);
		} else if (nodeName.equals("script")) {
			return new ScriptTag(node);
		} else if (nodeName.equals("field")) {
			return new FieldTag(node);
		} else if (nodeName.equals("filled")) {
			return new FilledTag(node);
		} else if (nodeName.equals("value")) {
			return new ValueTag(node);
		} else if (nodeName.equals("nomatch")) {
			return new NoMatchTag(node);
		} else if (nodeName.equals("catch")) {
			return new CatchTag(node);
		} else if (nodeName.equals("audio")) {
			return new AudioTag(node);
		} else if (nodeName.equals("assign")) {
			return new AssignTag(node);
		} else if (nodeName.equals("submit")) {
			return new SubmitTag(node);
		} else if (nodeName.equals("if")) {
			return new IfTag(node);
		} else if (nodeName.equals("elseif")) {
			return new ElseIfTag(node);
		} else if (nodeName.equals("foreach")) {
			return new ForEachTag(node);
		} else if (nodeName.equals("else")) {
			return new ElseTag(node);
		} else if (nodeName.equals("choice")) {
			return new ChoiceTag(node);
			// checks for cisco-data tag as well
		} else if (nodeName.endsWith("data")) {
			return new DataTag(node);
		} else if (nodeName.equals("log")) {
			return new LogTag(node);
		}

		return new NoActionTag(node);
	}

}
