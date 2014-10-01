package com.vxml.browser.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

import com.vxml.tag.Tag;
import com.vxml.tag.TagFactory;

public class VxmlParser {

	

	public void walk(Node node)  {
        if (!XmlUtils.isEmptyOrComment(node)) {
        	//TODO create tag
        	//call the start
        	//try the execute
        	System.out.println(node);
        	Tag tag = TagFactory.get(node);
        	
        	tag.startTag();
        	
        	if (!Tag.isSkipTagExecution()) {
        		tag.execute();
        	}
        	
        	System.out.println(tag);
            for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            	walk(child);
            }
            
            tag.endTag();
        }
    }

    public void parse(VxmlDoc vxmlDoc)  {
        Document doc = vxmlDoc.getXmlDoc();
        NodeIterator ni = vxmlDoc.createNodeIterator();
        walk(ni.nextNode());
    }

}
