package ch.ethz.inf.vs.a2.aenz.soapclient;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import ch.ethz.inf.vs.a2.aenz.http.Requester;
import ch.ethz.inf.vs.a2.aenz.sensor.AbstractSensor;

public class SoapSensor extends AbstractSensor{
	
	Requester req;
	
	public SoapSensor(){
		req = new SoapRequest();
	}

	@Override
	public void getTemperature() throws NullPointerException {
		
		new AsyncWorker().execute(new Requester[]{req});
		//TODO call asyncWorker
	}

	@Override
	public double parseResponse(String response) {
		return Double.NaN;
	}

}
