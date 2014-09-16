package com.vxml.tag;

import java.util.List;

import org.w3c.dom.Node;

import com.vxml.core.browser.VxmlBrowser;

//<foreach item="flavor" array="arrayFlavors">
// <prompt>
//  <value expr="flavor" />
//  <break />
// </prompt>
//</foreach>
public class ForeachTag extends AbstractTag {

	public ForeachTag(Node node) {
		super(node);
	}

	@Override
	public void execute() {
//		forEachTagCount++;
		String arrayVar = getAttribute("array");
		String item = getAttribute("item");
		VxmlBrowser.getContext().executeScript("var " + item);
		Object array = VxmlBrowser.getContext().executeScript(arrayVar);
		if (array instanceof List) {
			for (Object o : (List)array) {
				if (o instanceof String) {
					VxmlBrowser.getContext().executeScript(item + "='" + o + "'");
				} else {
					VxmlBrowser.getContext().executeScript(item + "=" + o);
				}
				executeChildTree(getNode().getFirstChild());
			}
		}
	}


}
