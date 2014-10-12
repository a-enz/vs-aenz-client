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
	
	private final int NONE = 0;
	private final int RAW = 1;
	private final int LIB = 2;
	private int status;
	private Handler handler;
	private Sensor libSensor;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        
        responseTxt = (TextView) findViewById(R.id.textViewResponse);
        status = 0;
        handler = new Handler();
        libSensor = SensorFactory.getInstance(SensorFactory.Type.HTML);
    }
    
    public void onClickRaw(View v) {
    	HttpTask request = new HttpTask(handler);
    	request.execute(new String[]{});
    	try {
			responseTxt.setText("Response Raw: " + request.get());
		} catch (InterruptedException e) {
			Log.d(TAG, "InterruptedException");
			e.printStackTrace();
		} catch (ExecutionException e) {
			Log.d(TAG, "ExecutionException");
			e.printStackTrace();
		}
    }
    
    public void onClickLib(View v) {
    	libSensor.registerListener(this);
    	libSensor.getTemperature();
    }
    
    public void onClickJson(View v) {
    	
    }
    
    @Override
    public void onStop(){
    	super.onStop();
    }	
    
    @Override
	public void onReceiveDouble(double value) {
		// TODO Auto-generated method stub
    	Log.d(TAG, "WTF NaN: " + (Double.NaN == Double.NaN));
	}

	@Override
	public void onReceiveString(String message) {
		Log.d(TAG, "Updating UI");
		responseTxt.setText(message);
	}
    
    private class HttpTask extends AsyncTask<String, String, String> {

    	private Handler handler;
    	
    	public HttpTask(Handler handler) {
    		this.handler = handler;
    	}
    	
		@Override
		protected String doInBackground(String... params) {
			try {
				ClientSocket socket = new ClientSocket();
				return socket.execute(null);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				return "Host not found";
			} catch (IOException e) {
				e.printStackTrace();
				return "Sth wrong with IO";
			}
		}
    }



}
