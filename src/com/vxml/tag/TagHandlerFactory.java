package com.vxml.tag;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;


public class TagHandlerFactory {

	public static Tag getTag(Node item) {
		String nodeName = item.getNodeName();
		if (nodeName.equals("vxml")) {
			return new VxmlTag(item);
		} else if (nodeName.equals("var")) {
			return new VarTag(item);
		} else if (nodeName.equals("form")) {
			return new FormTag(item);
		} 
		return new NoActionTag(item);
	}
	
	

	public StringWriter toXml(Node node) {
		StringWriter writer = new StringWriter();
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(node), new StreamResult(writer));
			
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer;
	}
	
//	public String toJson(Node node) {
//		StringWriter xmlString = toXml(node);
//		JSONObject xmlJSONObj = XML.toJSONObject(xmlString.toString());
//        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
//        System.out.println(jsonPrettyPrintString);
//	}

}
