package com.vxml.tag;

import java.net.URI;
import java.net.URISyntaxException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.vxml.core.VxmlPlayer;

public class SubmitTag extends AbstractTag {

    public SubmitTag(Node node) {
        super(node);
    }

    @Override
    public void execute() {
        if(!getNode().getNodeName().equals("catch")) {
            String exprValue = getAttribute("expr");
            String expr = null;
            if (exprValue != null) {
                expr = (String) executeScript(exprValue);
            }
            String next = getAttribute("next");
            String src = expr != null ? expr : next;
            
            StringBuilder queryParams = new StringBuilder();
            src= VxmlPlayer.context.getDocBase() + src;
            
            queryParams.append(src);
            queryParams.append("?");
            String namelist = getAttribute("namelist");
            String nameListArray[] = namelist.split(" ");
            for (int i = 0; i < nameListArray.length; i++) {
                queryParams.append(nameListArray[i]);
                queryParams.append("=");
                queryParams.append(executeScript(nameListArray[i] + ";"));
                queryParams.append("&");
            }
            Document result;
			try {
				//TODO enable POST method
				result = new DocumentStore().getDoc(new URI(queryParams.toString()));
				new VxmlDoc(result).play();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
        }
        
    }
    
}
