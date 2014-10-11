package ch.ethz.inf.vs.a2.aenz.httpclient;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import ch.ethz.inf.vs.a2.aenz.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientSocket implements HttpSocket, RemoteServerConfiguration{
	
	private final String TAG = "ClientSocket";
	
	private BufferedReader reader;
	private InputStreamReader in;
	private OutputStream out;
	private PrintWriter writer;
	private Socket socket;
	private String host;
	private int restPort;
	private final int LOCALPORT = 4321;
	private String reply;

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
		return (socket != null) ;//? socket.isConnected() : false;
	}

	@Override
	public String execute(String request) {
		try {
			//connection establishment
			socket = new Socket();
			socket.bind(null);
			socket.connect(new InetSocketAddress(host, restPort));
			
			
			in = new InputStreamReader(socket.getInputStream());
			reader = new BufferedReader(in);
			out = socket.getOutputStream();
			writer = new PrintWriter(out);
			
			Log.d("TAG", "Socket connected: " + Boolean.valueOf(isConnected()).toString());
			//writer.write(new ClientRequest().generateRequest(host, null));
			String simpleRequest = new ClientRequest().generateRequest(host+":"+restPort, "/sunspots");
			writer.print(simpleRequest);
			writer.write(simpleRequest);
			writer.flush();
			String tmp = reader.readLine();
			socket.close();
			Log.d(TAG, "Request send: " + simpleRequest);
			Log.d(TAG, "Response: " + tmp);
			return tmp;
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "adflj";
	}
}
