package com.vxml.tag.logic;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vxml.tag.AbstractTag;
import com.vxml.tag.Tag;
import com.vxml.tag.TagHandlerFactory;

public class IfTag extends AbstractTag {

	public IfTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		ifTagCount++;
		String cond = getAttribute("cond");
		Boolean isIfConditionTrue = (Boolean) executeScript(cond + ";");
		NodeList children = getNode().getChildNodes();
		int index = 0;
		while (index < children.getLength()) {
			Node n;
			n = children.item(index++);
			if (isIfConditionTrue) {
				do {
					Tag tag = TagHandlerFactory.getTag(n);
					((AbstractTag)tag).performTag();
				} while (isBlockIndicator(n));
				return;
			} else {
				while(!isBlockIndicator(n)) {
					n = children.item(index++);
				}
				Tag tag = TagHandlerFactory.getTag(n);
				((AbstractTag)tag).performTag();
			}
		}
		for (int i = 0; i < children.getLength(); i++) {
			// check the looping is over
			// looping is over when if block is executed
			// if one of the else block was executed
			// or if reached end of the nodes.
			if (isIfConditionTrue) {
				// execute the if block
			} else {
				// keep skipping the else until you get a true condition
			}
		}
		if (!isIfConditionTrue) {
			isSkip = true;
		}
	}

	private boolean isBlockIndicator(Node n) {
		if (n != null && "else".equals(n.getNodeName())
				|| "elseif".equals(n.getNodeName())) {
			return true;
		}
		return false;
	}

}
