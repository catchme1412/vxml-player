package com.vxml.tag;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.client.ClientProtocolException;
import org.w3c.dom.Document;

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
	
	public Document getDoc(String uri) {
		try {
			return getDoc(uri);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Document getDoc(URI uri) {
//		System.out.println("Fetch:" + uri);
		InputStream is =null;
		Document doc = null;
		try {
			is = getInputStream(uri);
//			is = new UrlFetchService().fetchInputStream(uri);
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true);
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			doc = builder.parse(is);
		} catch (Exception e) {
			System.err.println("FAILED TO FETCH:" + uri);
		} finally {
			try {
			    if (is != null) {
			        is.close();
			    }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return doc;
	}

	public InputStream getInputStream(URI uri) throws ClientProtocolException,
			IOException {
		InputStream is;
		String result = httpCaller.doGet(uri.toString());
		is = new ByteArrayInputStream(result.getBytes());
		return is;
	}
	
	public StringBuilder getData(String uri) {
		StringBuilder builder = new StringBuilder();
		try {
			InputStream in = getInputStream(new URI(uri));
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder;
	}
}
