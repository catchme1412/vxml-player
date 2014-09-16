package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.browser.event.Event;
import com.vxml.parser.VxmlDoc;

public class VxmlTag extends AbstractTag {

	public VxmlTag(Node item) {
		super(item);
	}

	@Override
	public void execute() throws Event {
		String application = getAttribute("application");
		if (application != null) {
			new VxmlDoc(application).play();
			System.err.println("APPLICATION URI IS DONE");
		} else {
		    
		}
		// after processing the app_root, start processing this document (goes
		// to stack first, when application is executed)
//		executeChildTree(getNode());
	}

}
