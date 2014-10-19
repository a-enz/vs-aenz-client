package ch.ethz.inf.vs.a2.aenz.sensor;

import java.util.Locale;
import java.util.Scanner;

import android.util.Log;
import ch.ethz.inf.vs.a2.aenz.http.Requester;
import ch.ethz.inf.vs.a2.aenz.httpclient.ClientRequester;

public class RawSensor extends AbstractSensor{

	ClientRequester requester;
	private static final String TAG = "RawSensor";
	private final String prefix = "<span class=\"getterValue\">";
	
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
		Scanner scanner = new Scanner(response.substring(response.indexOf(prefix) + prefix.length()));
		scanner.useDelimiter("<");
		Log.d(TAG, "Delimiter: " + scanner.delimiter().toString());
		scanner.useLocale(Locale.US);
		double res = scanner.nextDouble();
		scanner.close();
		return res;
	}

}
