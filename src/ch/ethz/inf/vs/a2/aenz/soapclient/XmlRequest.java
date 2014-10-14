package ch.ethz.inf.vs.a2.aenz.soapclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import ch.ethz.inf.vs.a2.aenz.http.RemoteServerConfiguration;
import ch.ethz.inf.vs.a2.aenz.http.Requester;

public class XmlRequest implements Requester,  RemoteServerConfiguration{
	
	private static final String TAG = "XmlRequest";
	
	private final String URL = "http://" + HOST + ":" + SOAP_PORT + "/SunSPOTWebServices/SunSPOTWebservice?wsdl";

	private HttpClient httpClient;
	private HttpPost httpPost;
	private String method = "getSpot";
	private String arg = "Spot3";
	
	
	public XmlRequest(){
		httpPost = new HttpPost(URL);
		String xmlSoap = createRequest(method,arg);
		try{
			StringEntity entity = new StringEntity(xmlSoap, HTTP.UTF_8);
			httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
			httpPost.setEntity(entity);
			
		} catch (Exception e){
			e.printStackTrace();
			Log.d(TAG, "configuring httpPost failed");
		}
		
		Log.d(TAG,"XML Request initialized");
		
	}
	
	private String createRequest(String method, String arg) {
		String res;
		
		res = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
				"<S:Header/>" +
				"<S:Body>" +
				"<ns2:" + method + " xmlns:ns2=\"http://webservices.vslecture.vs.inf.ethz.ch/\"";
		if(arg != null) {
			res += "><id>" + arg + "</id>" +
					"</ns2:" + method + ">";
		} else {
			res += "/>";
		}
		
		res +=  "</S:Body>" +
				"</S:Envelope>";
		
		return res;
	}
	
	@Override
	public String executeRequest() throws NullPointerException {
		httpClient = new DefaultHttpClient();
		
		try{
			Log.d(TAG,"HTTP REQUEST:\n" + EntityUtils.toString(httpPost.getEntity()));
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//Log.d(TAG,"HTTP RESPONSE\n" + EntityUtils.toString(httpResponse.getEntity()));
			return EntityUtils.toString(httpResponse.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
			return "EXCEPTION: httpPost call failed";
		}
	}

}
