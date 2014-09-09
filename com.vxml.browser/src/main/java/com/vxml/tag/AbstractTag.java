package com.vxml.tag;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vxml.core.VxmlPlayer;


public abstract class AbstractTag implements Tag {

	private static Map propertyMap = new HashMap();
	
	protected static boolean isSkip;
	
	private static Map<String, Tag> tagRef = new HashMap<String, Tag>();
	
	private Node node;
	
	public AbstractTag(Node node) {
		this.node = node;
	}

	public void performTag() {
			execute();
//		}
	}
	
	public Object executeScript(String script) {
		return VxmlPlayer.context.executeScript(script);
	}
	
	public Object executeScript(File file) {
	    return VxmlPlayer.context.executeScript(file);
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
	
	public void storeTag(String id, Tag tag) {
	    tagRef.put(id, tag);
	}
	
	public Tag retrieveTag(String id) {
	    return tagRef.get(id);
	}

	public void executeChildNodes() {
	    NodeList list = node.getChildNodes();
	    for(int i = 0; i < list.getLength(); i++) {
	        Node n = list.item(i);
	        Tag tag = TagHandlerFactory.getTag(n);
//	        System.out.println(nodeToString(tag.getNode()));
            ((AbstractTag) tag).performTag();
	    }
	}
	
	public static String nodeToString(Node node) {
	    StringWriter sw = new StringWriter();
	    try {
	      Transformer t = TransformerFactory.newInstance().newTransformer();
	      t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	      t.setOutputProperty(OutputKeys.INDENT, "yes");
	      t.transform(new DOMSource(node), new StreamResult(sw));
	    } catch (TransformerException te) {
	      System.out.println("nodeToString Transformer Exception");
	    }
	    return sw.toString();
	  }

}
