package ch.ethz.inf.vs.a2.aenz.soapclient;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import ch.ethz.inf.vs.a2.aenz.http.RemoteServerConfiguration;
import ch.ethz.inf.vs.a2.aenz.http.Requester;

public class XmlRequest implements Requester,  RemoteServerConfiguration{
	
	private final String URL = "http://" + HOST + ":" + SOAP_PORT + "/SunSPOTWebServices/SunSPOTWebservice?wsdl";

	private HttpClient httpClient;
	private HttpPost httpPost;
	private String method = "getDiscoveredSpots";
	private String arg = null;
	
	
	public XmlRequest(){
		httpPost = new HttpPost(URL);
		String xmlSoap = createRequest(method,arg);
		try{
			StringEntity entity = new StringEntity(xmlSoap, HTTP.UTF_8);
			httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
			httpPost.setEntity(entity);
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	private String createRequest(String method, String arg) {
		String res;
		
		res = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
				"<S:Header/>" +
				"<S:Body>" +
				"<ns2:" + method + " xmlns:ns2=\"http://webservices.vslecture.vs.inf.ethz.ch/\">";
		if(arg != null) res += "<id>" + arg + "</id>";
		
		res +=  "</ns2:getSpot>" +
				"</S:Body>" +
				"</S:Envelope>";
		
		return res;
	}
	
	@Override
	public String executeRequest() throws NullPointerException {
		httpClient = new DefaultHttpClient();
		
		return null;
	}

}
