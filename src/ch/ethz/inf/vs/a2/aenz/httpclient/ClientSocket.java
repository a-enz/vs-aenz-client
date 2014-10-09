package ch.ethz.inf.vs.a2.aenz.httpclient;

import ch.ethz.inf.vs.a2.aenz.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientSocket implements HttpSocket, RemoteServerConfiguration{
	
	private PrintWriter writer;
	private Socket socket;
	private String host;
	private int restPort;
	private final int LOCALPORT = 4321;

	public ClientSocket() throws UnknownHostException, IOException {
		host = HOST;
		restPort = REST_PORT;
		
	}
	@Override
	public String getHost() {
		return host;
	}

	@Override
	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public int getPort() {
		return restPort;
	}

	@Override
	public void setPort(int port) {
		this.restPort = port;
	}
	
	public boolean isConnected() {
		return (socket != null) ? socket.isConnected() : false;
	}

	@Override
	public String execute(String request) {
		
		try {
			socket = new Socket(host, restPort, InetAddress.getLocalHost(), LOCALPORT);
			
		} catch (IOException e) {
			e.printStackTrace();
			return "Connection could not be established";
		}
		return null;
	}

}
