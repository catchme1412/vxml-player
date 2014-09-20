package com.vxml.browser;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.concurrent.LinkedBlockingDeque;

import com.vxml.browser.event.Event;
import com.vxml.core.VxmlException;
import com.vxml.core.browser.VxmlBrowser;
import com.vxml.parser.event.OutputListener;
import com.vxml.tag.Tag;

public class VxmlBrowserVerifier {
	OutputListener audioEventListener;
	LinkedBlockingDeque<String> queue;
	private VxmlBrowser vxmlBrowser;
	
	private InputStream dtmfInput;
	
	public VxmlBrowserVerifier(VxmlBrowser vxmlBrowser) throws IOException {
		this.vxmlBrowser = vxmlBrowser;
		dtmfInput =  new ByteArrayInputStream("".getBytes());
		dtmfInput.reset();
		queue = new LinkedBlockingDeque<String>();
		audioEventListener = new OutputListener() {
			@Override
			public void invoke(Tag tag) {
				System.out.println(tag);
			}
		};
		vxmlBrowser.getContext().getEventHandler().register("audio", audioEventListener);
	}

	public void start() throws VxmlException, URISyntaxException, Event {
		vxmlBrowser.start();
	}

	public void inputDtmf(int i) {
//		dtmfInput.
	}

	public String next() {
		if (!queue.isEmpty()) {
			return queue.pop();
		}
		return null;
	}

	public String nextTTS() {

		return null;
	}

	public Object isDisconnected() {

		return null;
	}

}
