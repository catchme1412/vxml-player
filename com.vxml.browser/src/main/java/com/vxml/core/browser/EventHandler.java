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
		VxmlBrowser.getContext().executeScript("event='" + eventType + "'");
	}

    public Object getLastEvent() {
        return VxmlBrowser.getContext().executeScript("event");
    }

    public void clearEvent() {
        VxmlBrowser.getContext().executeScript("event=null;");        
    }

//	public boolean isEventFired(String eventType) {
//		return VxmlPlayer.context.isVarDefined("event");
//	}
}
