package com.vxml.tag;
import org.w3c.dom.Node;


public class TagHandlerFactory {

	public static Tag getTag(Node node) {
//		System.out.println("<<<<<" + node.getNodeType() + ":"+ node );
		
		if (node.getNodeType() == Node.TEXT_NODE) {
			return new TextTag(node);
		}
		String nodeName = node.getNodeName();
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
		}
		
		return new NoActionTag(node);
	}
	
	

//	public StringWriter toXml(Node node) {
//		StringWriter writer = new StringWriter();
//		Transformer transformer;
//		try {
//			transformer = TransformerFactory.newInstance().newTransformer();
//			transformer.transform(new DOMSource(node), new StreamResult(writer));
//			
//		} catch (TransformerConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TransformerFactoryConfigurationError e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TransformerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return writer;
//	}
	
//	public String toJson(Node node) {
//		StringWriter xmlString = toXml(node);
//		JSONObject xmlJSONObj = XML.toJSONObject(xmlString.toString());
//        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
//        System.out.println(jsonPrettyPrintString);
//	}

}
