package com.vxml.tag;

import org.w3c.dom.Node;

public class VxmlTag extends AbstractTag {

	public VxmlTag(Node item) {
		super(item);
	}

	@Override
	public void execute() {
		String application = getAttribute("application");
		if (application != null) {
			new VxmlDoc(application).play();
		}
		// after processing the app_root, start processing this document (goes
		// to stack first, when application is executed)
//		executeChildTree(getNode());
	}

}
