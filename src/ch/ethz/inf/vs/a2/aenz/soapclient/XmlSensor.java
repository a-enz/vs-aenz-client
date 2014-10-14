package ch.ethz.inf.vs.a2.aenz.soapclient;

import android.util.Log;
import ch.ethz.inf.vs.a2.aenz.http.Requester;
import ch.ethz.inf.vs.a2.aenz.sensor.AbstractSensor;

public class XmlSensor extends AbstractSensor {
	
	private static final String TAG = "XmlSensor";
	
	Requester req;
	
	public XmlSensor(){
		req = new XmlRequest();
	}

	@Override
	public void getTemperature() throws NullPointerException {
		new AsyncWorker().execute(new Requester[] {req});
	}

	@Override
	public double parseResponse(String response) {
		Integer tempOffset = response.indexOf("</temperature>");
		Log.d(TAG, tempOffset.toString());
		String tempVal = response.substring(tempOffset - 5, tempOffset - 1);
		Log.d(TAG, tempVal);
		return Double.parseDouble(tempVal);
	}

}
