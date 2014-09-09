package com.vxml.tag;

import org.w3c.dom.Node;


public class DataTag extends AbstractTag {


    public DataTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
	    StringBuilder queryParams = new StringBuilder();
		String src = getAttribute("src");
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
		StringBuilder result = new UrlFetchService().fetch(queryParams.toString());
		
		String name = getAttribute("name");
		executeScript("var "+ name + "='" + result.toString() + "';");
	}
	
	

}
