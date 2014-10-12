package ch.ethz.inf.vs.a2.aenz.soapclient;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import ch.ethz.inf.vs.a2.aenz.sensor.AbstractSensor;

public class SoapSensor extends AbstractSensor{
	
	public SoapSensor(){

	}

	@Override
	public void getTemperature() throws NullPointerException {
		try{
//			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//			
//			androidHttpTransport.call(SOAP_ACTION, soapEnv);
//			
//			response = (SoapObject) soapEnv.bodyIn;
			
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
