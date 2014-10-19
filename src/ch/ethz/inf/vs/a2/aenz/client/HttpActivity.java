package ch.ethz.inf.vs.a2.aenz.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import ch.ethz.inf.vs.a2.aenz.client.R;
import ch.ethz.inf.vs.a2.aenz.http.Requester;
import ch.ethz.inf.vs.a2.aenz.httpclient.ClientRequester2;
import ch.ethz.inf.vs.a2.aenz.httpclient.ClientSocket;
import ch.ethz.inf.vs.a2.aenz.sensor.LibSensor;
import ch.ethz.inf.vs.a2.aenz.sensor.Sensor;
import ch.ethz.inf.vs.a2.aenz.sensor.SensorFactory;
import ch.ethz.inf.vs.a2.aenz.sensor.SensorListener;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class HttpActivity extends Activity implements SensorListener{
	
	private TextView responseTxt;
	private final String TAG = "HttpActivity";
	
	private Handler handler;
	private Sensor libSensor;
	private Sensor jsonSensor;
	private Sensor rawSensor;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        
        responseTxt = (TextView) findViewById(R.id.textViewResponse);
        handler = new Handler();
        libSensor = SensorFactory.getInstance(SensorFactory.Type.HTML);
        jsonSensor = SensorFactory.getInstance(SensorFactory.Type.JSON);
        rawSensor = SensorFactory.getInstance(SensorFactory.Type.RAW_HTTP);
    	jsonSensor.registerListener(this);
    	libSensor.registerListener(this);
    	rawSensor.registerListener(this);
    }
    
    public void onClickRaw(View v) {
    	rawSensor.getTemperature();
    	//rawSensor.unregisterListener(this);
    }
    
    public void onClickLib(View v) {
    	libSensor.getTemperature();
    	//libSensor.unregisterListener(this);
    }
    
    public void onClickJson(View v) {

    	jsonSensor.getTemperature();
    	//jsonSensor.unregisterListener(this);
    }
    
    @Override
    public void onStop(){
    	super.onStop();
    }	
    
    @Override
	public void onReceiveDouble(double value) {
    	Log.d(TAG, "Updating Double UI");
    	responseTxt.setText("Temperature Spot1: " + value);
	}

	@Override
	public void onReceiveString(String message) {
		Log.d(TAG, "Updating String UI");
		//responseTxt.setText(message);
	}
}
