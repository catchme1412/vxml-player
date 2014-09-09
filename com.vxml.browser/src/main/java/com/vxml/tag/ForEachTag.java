package com.vxml.tag;

import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
		executeScript("var " + item +";");
		Object array = executeScript(arrayVar +";");
		if (array instanceof List) {
			for (Object o : (List)array) {
				executeScript(item + "='" + o + "';");
				System.out.println(o);

				NodeList list = getNode().getChildNodes();
				for (int i = 0; i < list.getLength(); i++) {
					Node n = list.item(i);
					Tag tag = TagHandlerFactory.getTag(n);
					 System.out.println(nodeToString(tag.getNode()));
					 System.out.println(getNode().getTextContent().trim());
					((AbstractTag) tag).execute();
				}
			
			}
		}
	}


}
