package ch.ethz.inf.vs.a2.aenz.soapclient;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import ch.ethz.inf.vs.a2.aenz.sensor.AbstractSensor;

public class SoapSensor extends AbstractSensor{
	
	private final String URL = "http://schemas.xmlsoap.org/soap/envelope/";
	private final String SOAP_ACTION = "http://webservices.vslecture.vs.inf.ethz.ch/getSpot";
	private final String NAMESPACE = "http.//webservices.vslecture.vs.inf.ethz.ch/";
	private final String METHOD_NAME = "getSpot";
	private final String ARG = "Spot3";
	
	private SoapObject request;
	private SoapObject response;
	private SoapSerializationEnvelope soapEnv;
	
	public SoapSensor(){
		request = new SoapObject(NAMESPACE, METHOD_NAME);
		request.addProperty("Spot", ARG);
		
		soapEnv = new SoapSerializationEnvelope(SoapEnvelope.VER10); //no idea what version is required
		soapEnv.setOutputSoapObject(request);
		soapEnv.dotNet = true;
	}

	@Override
	public void getTemperature() throws NullPointerException {
		try{
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			
			androidHttpTransport.call(SOAP_ACTION, soapEnv);
			
			response = (SoapObject) soapEnv.bodyIn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public double parseResponse(String response) {
		// TODO do something with 'response' 
		return 0;
	}

}
