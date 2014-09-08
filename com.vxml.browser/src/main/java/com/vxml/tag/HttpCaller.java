package com.vxml.tag;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * An implementation of HTTP_Caller using the Apache Commons of HttpClient 
 *
 */
public class HttpCaller  {

	private HttpClient client = null;
	
	public void startSession() {
		client = new DefaultHttpClient();
	}
	
	public void releaseSession() {
		client = null;
	}

	/**
	 * GET method
	 * 
	 * @param url
	 * @return String
	 */
	public String doGET(String url) {
		
		// Create a GET method instance.
		GetMethod method = new GetMethod(url);

		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		
		String returnedString = null;
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();
			returnedString = new String(responseBody);
	
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return returnedString;

	}
	
	/**
	 * POST method
	 * 
	 * @param url
	 * @return String
	 */
	public String doPOST(String url) {
		
		// Create a POST method instance.
		PostMethod method = new PostMethod(url);

		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		
		String returnedString = null;
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();
			returnedString = new String(responseBody);
	
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return returnedString;

	}
	
}
