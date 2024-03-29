package ch.ethz.inf.vs.a2.aenz.sensor;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;
import ch.ethz.inf.vs.a2.aenz.http.Requester;

/**
 * Implementation of a sensor representation.
 * 
 * @author Leyna Sadamori
 * 
 * @see Sensor
 * @see ResponseParser
 * @see SensorListener
 *
 */
public abstract class AbstractSensor implements Sensor, ResponseParser {
	protected String name = null;
	protected List<SensorListener> listeners = new ArrayList<SensorListener>();

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void registerListener(SensorListener listener) {
		listeners.add(listener);
	}

	@Override
	public void unregisterListener(SensorListener listener) {
		listeners.remove(listener);
	}

	/**
	 * AsyncTask to execute the request in a seperate thread. This AsyncTask
	 * makes use of the Requester interface to support different implementations
	 * of making a request. The response will be parsed according to the
	 * implementation of the ResponseParser interface. Finally, all listeners
	 * are notified about the result.
	 * 
	 * @author Leyna Sadamori
	 * 
	 * @see Requester
	 * @see ResponseParser
	 *
	 */
	public class AsyncWorker extends AsyncTask<Requester, Void, String> {

		@Override
		protected String doInBackground(Requester... params) {
			return params[0].executeRequest();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			double value = parseResponse(result);
			if (!Double.isNaN(value)) {
				for (SensorListener listener : listeners) {
					listener.onReceiveDouble(value);
				}
			} else {
				for (SensorListener listener : listeners) {
					listener.onReceiveString(result);
					Log.d("AsyncWorker", "Notify from: " + listener.toString());
				}
			}
		}
	}
}
