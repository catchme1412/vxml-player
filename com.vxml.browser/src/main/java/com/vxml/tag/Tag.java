package com.vxml.tag;
import org.w3c.dom.Node;


public interface Tag {

	public Node getNode();
	public void execute();
    public void executeChildNodes();
    public void executeChildTree(Node startingNode);
}
