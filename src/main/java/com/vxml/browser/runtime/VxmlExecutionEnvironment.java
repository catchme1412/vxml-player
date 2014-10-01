package com.vxml.browser.runtime;

import java.net.URI;
import java.net.URISyntaxException;

import org.w3c.dom.Document;

import com.vxml.browser.parser.VxmlDoc;
import com.vxml.store.DocumentStore;

public class VxmlExecutionEnvironment {
	private URI entryPointUrl;
	private URI documentBaseUri;
	
	private DocumentStore documentStore;
	
	private EventHandler outputEventHandler;
	
	public VxmlExecutionEnvironment() {
		outputEventHandler = new EventHandler();
	}
	public URI getEntryPointUrl() {
		return entryPointUrl;
	}

	public void setEntryPointUrl(URI entryPointUrl) {
		this.entryPointUrl = entryPointUrl;
	}

	public URI getDocumentBaseUri() {
		return documentBaseUri;
	}

	public void setDocumentBaseUri(String entryPointUri) throws URISyntaxException {
		URI uri = new URI(entryPointUri);
		String documentBaseUri = uri.getScheme() +"://" + uri.getAuthority();
		this.documentBaseUri = new URI(documentBaseUri);
	}
	
	public VxmlDoc getDoc(String uri) throws URISyntaxException {
		documentStore = new DocumentStore();
		Document xmlDoc = documentStore.getDoc(new URI(uri));
        return new VxmlDoc(xmlDoc);
	}
	
	public void playTTS(String text) {
		outputEventHandler.add(new Event());
	}

	
}
