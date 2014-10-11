package ch.ethz.inf.vs.a2.aenz.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpGet;

import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.util.Log;

import ch.ethz.inf.vs.a2.aenz.http.RemoteServerConfiguration;
import ch.ethz.inf.vs.a2.aenz.http.Requester;

public class ClientRequester2 implements Requester, RemoteServerConfiguration{
	
	//private final HttpGet HTTP_REQUEST = new HttpGet(Uri.encode("http://www." + HOST + ":" + REST_PORT + "/sunspots"));
	private final String URL = "http://" + HOST + ":" + REST_PORT + "/sunspots";
	private final HttpGet HTTP_REQUEST = new HttpGet(URL);
	private AndroidHttpClient httpClient;
	private BufferedReader reader;
	private final String TAG = "ClientRequester2";
	
	public ClientRequester2() {
		httpClient = AndroidHttpClient.newInstance("Lib");
	}
	@Override
	public String executeRequest() throws NullPointerException {
		try {
			//HTTP_REQUEST.
			Log.d(TAG, "Request: " + HTTP_REQUEST.getURI());
			reader = new BufferedReader(new InputStreamReader(httpClient.execute(HTTP_REQUEST).getEntity().getContent()));
			String res = "";
			String tmp = null;
			while((tmp = reader.readLine()) != null) {
				res = res + tmp;
			}
			return res;
		} catch (IllegalStateException e) {
			Log.d(TAG, "IllegalStateException");
			e.printStackTrace();
			return "IllegalStateException";
		} catch (IOException e) {
			Log.d(TAG, "IOException");
			e.printStackTrace();
			return "IOException";
		} catch (IllegalArgumentException e) {
			Log.d(TAG, "IllegalArgumentException");
			Log.d(TAG, HTTP_REQUEST.toString());
			e.printStackTrace();
			return "IllegalArgumentException";
		}
	}

}
