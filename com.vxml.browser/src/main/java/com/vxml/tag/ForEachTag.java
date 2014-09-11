package com.vxml.tag;

import java.util.List;

import org.w3c.dom.Node;

//<foreach item="flavor" array="arrayFlavors">
// <prompt>
//  <value expr="flavor" />
//  <break />
// </prompt>
//</foreach>
public class ForEachTag extends AbstractTag {

	public ForEachTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
		forEachTagCount++;
		String arrayVar = getAttribute("array");
		String item = getAttribute("item");
		executeScript("var " + item);
		Object array = executeScript(arrayVar);
		if (array instanceof List) {
			for (Object o : (List)array) {
				if (o instanceof String) {
					executeScript(item + "='" + o + "'");
				} else {
					executeScript(item + "=" + o);
				}
				executeChildTree(getNode());
			}
			isSkipTag = true;
		}
	}


}
