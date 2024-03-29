package ch.ethz.inf.vs.a2.aenz.soapclient;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import ch.ethz.inf.vs.a2.aenz.http.Requester;
import ch.ethz.inf.vs.a2.aenz.http.RemoteServerConfiguration;

public class SoapRequest implements Requester, RemoteServerConfiguration{
	
	private static final String TAG = "SoapRequest";

	private final String URL = "http://" + HOST + ":" + SOAP_PORT + "/SunSPOTWebServices/SunSPOTWebservice?wsdl";
	private final String NAMESPACE = "http://webservices.vslecture.vs.inf.ethz.ch/";
	private final String METHOD_NAME = "getSpot";
	private final String ARG = "Spot3";
	private final String ARG_NAME = "id";
	
	private SoapObject request;
	private SoapObject response;
	private SoapSerializationEnvelope soapEnv;
	
	public SoapRequest() {
		request = new SoapObject(NAMESPACE, METHOD_NAME);
		
		request.addProperty(ARG_NAME, ARG);
		
		soapEnv = new SoapSerializationEnvelope(SoapEnvelope.VER11); //no idea what version is required
		soapEnv.setOutputSoapObject(request);
		//soapEnv.dotNet = true;
		soapEnv.implicitTypes = true;
		soapEnv.setAddAdornments(false);
	}
	
	
	@Override
	public String executeRequest() throws NullPointerException {
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.debug = true; //capture raw data in logcat
		try{

			
			Log.d(TAG,"attempting http call to: " + URL);
			androidHttpTransport.call(NAMESPACE + METHOD_NAME, soapEnv);
			
			Log.d(TAG, "call was successfull");
			
			response = (SoapObject) soapEnv.bodyIn;
			Log.d(TAG, "count of soap properties: " + response.getPropertyCount());
			response = (SoapObject) response.getProperty(0);
			return response.getPropertyAsString(5);
		} catch (Exception e){
			Log.d(TAG, "HTTP REQUEST:\n" + androidHttpTransport.requestDump);
			Log.d(TAG, "HTTP RESPONSE:\n" + androidHttpTransport.responseDump);
			e.printStackTrace();
			return "EXCEPTION: http call failed";			
		}
	}
	
	public SoapObject getResponse(){
		return response;
	}

}
