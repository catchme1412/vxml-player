package com.vxml.tag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class UrlFetchService {

	private static Logger logger = Logger.getLogger(UrlFetchService.class
			.getName());

	public InputStream fetchInputStream(URI uri) throws IOException {
		URL url = uri.toURL();
		URLConnection connection = url.openConnection();
		logger.info("URL: " + url.toExternalForm());
		return connection.getInputStream();
	}

}
