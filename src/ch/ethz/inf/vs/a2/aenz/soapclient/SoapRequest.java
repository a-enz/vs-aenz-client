package ch.ethz.inf.vs.a2.aenz.soapclient;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import ch.ethz.inf.vs.a2.aenz.http.Requester;
import ch.ethz.inf.vs.a2.aenz.http.RemoteServerConfiguration;

public class SoapRequest implements Requester, RemoteServerConfiguration{
	
	private static final String TAG = "SoapRequest";

	private final String URL = "http://" + HOST + ":" + REST_PORT + "/SunSPOTWebServices/SunSPOTWebservice?wsdl";
	private final String SOAP_ACTION = "http://webservices.vslecture.vs.inf.ethz.ch/getSpot";
	private final String NAMESPACE = "http://webservices.vslecture.vs.inf.ethz.ch/";
	private final String METHOD_NAME = "getSpot";
	private final String ARG = "Spot3";
	
	private SoapObject request;
	private SoapObject response;
	private SoapSerializationEnvelope soapEnv;
	
	public SoapRequest() {
		request = new SoapObject(NAMESPACE, METHOD_NAME);
		request.addProperty("getSpot", ARG);
		
		soapEnv = new SoapSerializationEnvelope(SoapEnvelope.VER10); //no idea what version is required
		soapEnv.setOutputSoapObject(request);
		soapEnv.dotNet = true;
	}
	
	
	@Override
	public String executeRequest() throws NullPointerException {
		try{

			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			
			Log.d(TAG,"attempting http call");
			androidHttpTransport.call(SOAP_ACTION, soapEnv);
			
			Log.d(TAG,"http call done");
			response = (SoapObject) soapEnv.bodyIn;
			return (String) soapEnv.bodyIn;
		} catch (Exception e){
			Log.d(TAG, soapEnv.bodyOut.toString());
			return "EXCEPTION: http call failed";			
		}
	}
	
	public SoapObject getResponse(){
		return response;
	}

}
