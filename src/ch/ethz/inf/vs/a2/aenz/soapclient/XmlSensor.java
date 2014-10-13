package ch.ethz.inf.vs.a2.aenz.soapclient;

import ch.ethz.inf.vs.a2.aenz.http.Requester;
import ch.ethz.inf.vs.a2.aenz.sensor.AbstractSensor;

public class XmlSensor extends AbstractSensor {
	
	Requester req;
	
	public XmlSensor(){
		req = new XmlRequest();
	}

	@Override
	public void getTemperature() throws NullPointerException {
		
	}

	@Override
	public double parseResponse(String response) {
		// TODO Auto-generated method stub
		return 0;
	}

}
