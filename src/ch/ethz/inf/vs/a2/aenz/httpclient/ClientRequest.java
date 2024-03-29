package ch.ethz.inf.vs.a2.aenz.httpclient;

import ch.ethz.inf.vs.a2.aenz.http.HttpRawRequest;

public class ClientRequest implements HttpRawRequest{

	//final String getHeader = "GET /sites/net/0902231.htm HTTP/1.1\r\n";
	final String GET1 = "GET ";
	final String GET2 = " HTTP/1.1\r\n";
	final String HOST = "Host: ";
	final String CONNECTION = "Connection: keep-alive\r\n";
	final String CACHE = "Cache-Control: no-cache\r\n";
	final String ACCEPT = "Accept: */*\r\nAccept-Encoding: gzip, deflate, sdch\r\nAccept-Language: en-Us,en;q=0.8,de;q=0.6\r\n";
	final String END = "\r\n";
	final String dummy = "GET /sunspots HTTP/1.1\r\nHost: vslab.inf.ethz.ch:8081\r\nConnection: keep-alive\r\nCache-Control: no-cache\r\nAccept: */*\r\nAccept-Encoding: gzip, deflate, sdch\r\nAccept-Language: en-Us,en;q=0.8,de;q=0.6\r\n\r\n";

	
	/**
	 * Generates the (very simple) HTTP Request
	 * 
	 * @param host url with port number
	 * @param path path starting with a "/"
	 */
	@Override
	public String generateRequest(String host, String path) {
		return GET1 + path + GET2 + HOST + host + "\r\n" + CACHE + ACCEPT + END;
	}

	@Override
	public String generateRequest(String host, int port, String path) {
		return GET1 + path + GET2 + HOST + host + ":" + port + "\r\n" + CACHE + ACCEPT + END;
	}

}
