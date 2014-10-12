package ch.ethz.inf.vs.a2.aenz.soapclient;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import ch.ethz.inf.vs.a2.aenz.http.Requester;

public class SoapRequest implements Requester{
	
	private final String URL = "http://schemas.xmlsoap.org/soap/envelope/";
	private final String SOAP_ACTION = "http://webservices.vslecture.vs.inf.ethz.ch/getSpot";
	private final String NAMESPACE = "http.//webservices.vslecture.vs.inf.ethz.ch/";
	private final String METHOD_NAME = "getSpot";
	private final String ARG = "Spot3";
	
	private SoapObject request;
	private SoapObject response;
	private SoapSerializationEnvelope soapEnv;
	
	public SoapRequest() {
		
	}
	
	
	@Override
	public String executeRequest() throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

}
