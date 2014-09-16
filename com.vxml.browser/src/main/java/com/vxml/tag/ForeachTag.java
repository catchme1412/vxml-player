package com.vxml.tag;

import java.util.List;

import org.w3c.dom.Node;

import com.vxml.browser.event.Event;
import com.vxml.core.browser.VxmlBrowser;

//<foreach item="flavor" array="arrayFlavors">
// <prompt>
//  <value expr="flavor" />
//  <break />
// </prompt>
//</foreach>
public class ForeachTag extends AbstractTag {

	private boolean isSkipTagBackup;

	public ForeachTag(Node node) {
		super(node);
	}

	@Override
	public void startTag() {
		isSkipTagBackup = isSkipExecute();
	}

	@Override
	public void execute() throws Event {

		setSkipExecute(false);
		String arrayVar = getAttribute("array");
		String item = getAttribute("item");
		VxmlBrowser.getContext().executeScript("var " + item);
		Object array = VxmlBrowser.getContext().executeScript(arrayVar);
		if (array instanceof List) {
			for (Object o : (List) array) {
				if (o instanceof String) {
					VxmlBrowser.getContext().executeScript(item + "='" + o + "'");
				} else {
					VxmlBrowser.getContext().executeScript(item + "=" + o);
				}
				System.out.println("LOOOP:" + o);
				executeChildTree(getNode());
				// setSkipExecute(true);
			}
		}
		setSkipExecute(true);
	}

	@Override
	public void endTag() {
		setSkipExecute(isSkipTagBackup);
	}

}
