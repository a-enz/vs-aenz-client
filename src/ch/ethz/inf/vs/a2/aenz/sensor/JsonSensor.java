package ch.ethz.inf.vs.a2.aenz.sensor;

import ch.ethz.inf.vs.a2.aenz.http.Requester;
import ch.ethz.inf.vs.a2.aenz.httpclient.ClientRequester2;
import org.json.*;

public class JsonSensor extends AbstractSensor{

	private ClientRequester2 requester;
	private JSONObject parser;
	
	
	public JsonSensor() {
		requester = new ClientRequester2(true);

	}
	
	@Override
	public void getTemperature() throws NullPointerException {
		new AsyncWorker().execute(new Requester[]{requester});
		
	}

	@Override
	public double parseResponse(String response) {
		JSONTokener tokener = new JSONTokener(response);
		try {
			parser = new JSONObject(tokener);
			return parser.getDouble("value");
		} catch (JSONException e) {
			e.printStackTrace();
			return -999.0;
		}
	}

}
