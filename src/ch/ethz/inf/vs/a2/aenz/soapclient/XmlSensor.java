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
		//shitty hardcoded version. Don't ask for anything else but temperature
		Integer tEnd = response.indexOf("</temperature>");
		Integer tStart = response.indexOf("<temperature>") + 12;
		Log.d(TAG, tEnd.toString() + " // " + tStart.toString());
		String tempVal = response.substring(tStart + 1, tEnd);
		Log.d(TAG, tempVal);
		return Double.parseDouble(tempVal);
	}

}
