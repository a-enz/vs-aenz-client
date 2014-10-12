package ch.ethz.inf.vs.a2.aenz.sensor;

import ch.ethz.inf.vs.a2.aenz.soapclient.SoapSensor;


public abstract class SensorFactory {
	public static Sensor getInstance(Type type) {
		switch (type) {
		case RAW_HTTP:
			// return Sensor implementation using a raw HTTP request
		case HTML:
			// return Sensor implementation using text/html representation
			return new LibSensor();
		case JSON:
			// return Sensor implementation using application/json representation
		case XML:
			// return Sensor implementation using application/xml representation
		case SOAP:
			// return Sensor implementation using a SOAPObject
			return new SoapSensor();
		default:
			return null;
		}
	}
	
	public enum Type {
		RAW_HTTP, HTML, JSON, XML, SOAP;
	}
}
