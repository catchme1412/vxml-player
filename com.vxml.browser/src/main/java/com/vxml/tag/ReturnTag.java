package com.vxml.tag;

import org.w3c.dom.Node;

import com.vxml.browser.event.ReturnFromSubdialogEvent;
import com.vxml.core.VxmlException;

public class ReturnTag extends AbstractTag {

	public ReturnTag(Node node) {
		super(node);
	}

	@Override
	public void startTag() {
		if (null != getSubdialogName()) {
			throw new VxmlException("<return> expected inside <subdialog> only");
		}
	}

	@Override
	public void execute() throws ReturnFromSubdialogEvent {
		throw new ReturnFromSubdialogEvent();
	}

	@Override
	public void endTag() {
	}

}
