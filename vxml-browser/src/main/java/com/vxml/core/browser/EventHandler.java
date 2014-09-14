package com.vxml.core.browser;

import java.util.HashMap;
import java.util.Map;

import com.vxml.tag.Tag;

public class EventHandler {

	private Map<String, Tag> eventMap = new HashMap<String, Tag>();

	public void addEventHandle(String eventName, Tag node) {
		eventMap.put(eventName, node);
	}

	public void handleEvent(String eventType) {
		eventMap.get(eventType).execute();
	}

	

	public void fireEvent(String eventType) {
//		VxmlPlayer.context.executeScript("var event='" + eventType + "'");
//		handleEvent(eventType);
	}

//	public boolean isEventFired(String eventType) {
////		return VxmlPlayer.context.isVarDefined("event");
//	}
}
