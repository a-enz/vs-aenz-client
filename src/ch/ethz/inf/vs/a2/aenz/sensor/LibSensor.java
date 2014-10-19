package ch.ethz.inf.vs.a2.aenz.sensor;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import android.util.Log;
import ch.ethz.inf.vs.a2.aenz.http.Requester;
import ch.ethz.inf.vs.a2.aenz.httpclient.ClientRequester2;

public class LibSensor extends AbstractSensor{

	private ClientRequester2 requester;
	private static final String TAG = "LibSensor";
	private final String prefix = "<span class=\"getterValue\">";
	
	public LibSensor() {
		requester = new ClientRequester2(false);
	}
	
	@Override
	public void getTemperature() throws NullPointerException {
		// TODO Auto-generated method stub
		Log.d(TAG, "getting temperature");
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
