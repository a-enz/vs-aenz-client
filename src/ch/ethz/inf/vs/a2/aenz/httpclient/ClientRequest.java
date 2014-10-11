package ch.ethz.inf.vs.a2.aenz.httpclient;

import ch.ethz.inf.vs.a2.aenz.http.HttpRawRequest;

public class ClientRequest implements HttpRawRequest{

	//final String getHeader = "GET /sites/net/0902231.htm HTTP/1.1\r\n";
	final String GET = "GET /HTTP/1.1\r\n";
	final String KEEPALIVE = "Keep-Alive: 300\r\n";
	final String CONNECTION = "Connection: Keep-Alive\r\n";
	final String HOST = "Host: ";
	
	
	@Override
	public String generateRequest(String host, String path) {
		
		return GET + HOST + host + "\r\n" + KEEPALIVE + CONNECTION;
	}

	@Override
	public String generateRequest(String host, int port, String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
