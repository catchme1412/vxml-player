package com.vxml.tag;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import sun.nio.cs.StandardCharsets;

import com.vxml.http.HttpCaller;


public class DocumentStore {

	private static HttpCaller httpCaller;
	
	public DocumentStore() {
		if (httpCaller == null) {
			httpCaller = new HttpCaller();
			httpCaller.startSession();
		}
	}
	
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
//		System.out.println("Fetch:" + uri);
		InputStream is =null;
		Document doc = null;
		try {
			String result = httpCaller.doGet(uri.toString());
			is = new ByteArrayInputStream(result.getBytes());
//			is = new UrlFetchService().fetchInputStream(uri);
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true);
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			doc = builder.parse(is);
		} catch (Exception e) {
			System.err.println("FAILED TO FETCH:" + uri);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return doc;
	}
}
