package com.vxml.tag;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class VxmlTag extends AbstractTag {

	
	public VxmlTag(Node item) {
		super(item);
	}
	
	@Override
	public void execute() {
		String application = getAttribute("application");
		if(application != null) {
			new VxmlDoc(application).play();
		}
		//after processing the app_root, start processing this document (goes to stack first)
		NodeList children = getNode().getChildNodes();
		for (int i=0 ; i< children.getLength(); i++) {
		    Tag tag = TagHandlerFactory.getTag(children.item(i));
		    tag.execute();
		}
	}

}
