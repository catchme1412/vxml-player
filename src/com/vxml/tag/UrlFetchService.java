package com.vxml.tag;

import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class UrlFetchService {

	private static Logger logger = Logger.getLogger(UrlFetchService.class
			.getName());

	public InputStream fetchInputStream(URI uri) throws IOException {
		URL url = uri.toURL();
		CookieHandler.setDefault( new CookieManager( null, CookiePolicy.ACCEPT_ALL ) );
		URLConnection connection = url.openConnection();
		System.out.println("JSESSIONID:" + connection.getHeaderField("jsessionid"));
		logger.info("URL: " + url.toExternalForm());
		return connection.getInputStream();
	}

}
