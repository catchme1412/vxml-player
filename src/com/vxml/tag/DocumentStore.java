package com.vxml.tag;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


public class DocumentStore {

	public VxmlDoc get(URI uri) {
		try {
			Document doc = getDoc(uri);
	        return new VxmlDoc(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Failed to get " + uri);
		
	}

	public Document getDoc(URI uri) {
		System.out.println("Fetch:" + uri);
		InputStream is =null;
		Document doc = null;
		try {
			is = new UrlFetchService().fetchInputStream(uri);
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true);
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			doc = builder.parse(is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return doc;
	}
}
