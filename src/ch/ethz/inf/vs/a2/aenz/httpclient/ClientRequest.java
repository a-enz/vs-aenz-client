package ch.ethz.inf.vs.a2.aenz.httpclient;

import ch.ethz.inf.vs.a2.aenz.http.HttpRawRequest;

public class ClientRequest implements HttpRawRequest{

	//final String getHeader = "GET /sites/net/0902231.htm HTTP/1.1\r\n";
	final String GET1 = "GET ";
	final String GET2 = "/HTTP/1.1\r\n";
	final String KEEPALIVE = "Keep-Alive: 300\r\n";
	final String CONNECTION = "Connection: close\r\n";
	final String HOST = "Host: ";
	final String CACHE = "Cache-Control: no-cache\r\n";
	
	/**
	 * Generates the (very simple) HTTP Request
	 * 
	 * @param host url with port number
	 * @param path path starting with a "/"
	 */
	@Override
	public String generateRequest(String host, String path) {
		
		//return GET1 + path + GET2 + HOST + host + "\r\n" ;//+ KEEPALIVE + CONNECTION;
		return GET1 + path + GET2 + HOST + host + "\r\n" + CACHE + CONNECTION;
	}

	@Override
	public String generateRequest(String host, int port, String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
