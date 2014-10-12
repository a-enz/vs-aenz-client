package ch.ethz.inf.vs.a2.aenz.sensor;

import android.util.Log;
import ch.ethz.inf.vs.a2.aenz.http.Requester;
import ch.ethz.inf.vs.a2.aenz.httpclient.ClientRequester2;

public class LibSensor extends AbstractSensor{

	private ClientRequester2 requester;
	private static final String TAG = "LibSensor";
	
	public LibSensor() {
		requester = new ClientRequester2();
	}
	
	@Override
	public void getTemperature() throws NullPointerException {
		// TODO Auto-generated method stub
		Log.d(TAG, "getting temperature");
		new AsyncWorker().execute(new Requester[]{requester});
	}

	@Override
	public double parseResponse(String response) {
		// TODO Auto-generated method stub
		return Double.NaN;
	}

}
