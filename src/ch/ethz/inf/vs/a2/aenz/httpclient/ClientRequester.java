package ch.ethz.inf.vs.a2.aenz.httpclient;

import java.io.IOException;
import java.net.UnknownHostException;

import ch.ethz.inf.vs.a2.aenz.http.RemoteServerConfiguration;
import ch.ethz.inf.vs.a2.aenz.http.Requester;

public class ClientRequester implements Requester, RemoteServerConfiguration{

	ClientRequest request;
	ClientSocket socket;
	private final String PATH = "/sunspots/Spot1/sensors/temperature";
	
	public ClientRequester() {
		request = new ClientRequest();
		try {
			socket = new ClientSocket();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String executeRequest() throws NullPointerException {
		return socket.execute(request.generateRequest(HOST, REST_PORT, PATH));
	}

}
