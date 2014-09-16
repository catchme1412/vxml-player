package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.browser.event.Event;
import com.vxml.core.browser.VxmlBrowser;
import com.vxml.dtmf.DtmfInput;
import com.vxml.parser.VxmlDoc;

public class ChoiceTag extends AbstractTag {

	public ChoiceTag(Node node) {
		super(node);
	}

	@Override
	public void execute() throws Event {
		String dtmf = getAttribute("dtmf");
		String value = new DtmfInput().read();
		if (dtmf.equals(value)) {
		    String expr = getAttribute("expr");
		    Object target = VxmlBrowser.getContext().executeScript(expr);
		    new VxmlDoc(target.toString()).play();
		}
	}
}
