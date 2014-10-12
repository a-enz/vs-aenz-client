package ch.ethz.inf.vs.a2.aenz.sensor;

import android.util.Log;
import ch.ethz.inf.vs.a2.aenz.http.Requester;
import ch.ethz.inf.vs.a2.aenz.httpclient.ClientRequester;

public class RawSensor extends AbstractSensor{

	ClientRequester requester;
	private static final String TAG = "RawSensor";
	
	public RawSensor() {
		requester = new ClientRequester();
	}
	
	@Override
	public void getTemperature() throws NullPointerException {
		Log.d(TAG, "Getting temperature...");
		new AsyncWorker().execute(new Requester[]{requester});
	}

	@Override
	public double parseResponse(String response) {
		return Double.NaN;
	}

}
