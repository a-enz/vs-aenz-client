package ch.ethz.inf.vs.a2.aenz.client;

import java.io.IOException;

import ch.ethz.inf.vs.a2.aenz.sensor.AbstractSensor;
import ch.ethz.inf.vs.a2.aenz.sensor.Sensor;
import ch.ethz.inf.vs.a2.aenz.sensor.SensorFactory;
import ch.ethz.inf.vs.a2.aenz.sensor.SensorListener;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SoapActivity extends Activity implements SensorListener {
	
	TextView disp;
	Sensor soapSensor, xmlSensor;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soapclient);
		
		disp = (TextView) findViewById(R.id.txt_soap_disp);
		
		soapSensor = SensorFactory.getInstance(SensorFactory.Type.SOAP);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.soapclient, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onReceiveDouble(double value) {
		// TODO Auto-generated method stub
		disp.setText("Temperature at Spot3: " + value);
	}

	@Override
	public void onReceiveString(String message) {
		// TODO Auto-generated method stub
		disp.setText(message);
	}
	
	public void onClickSoapManual(View v) {
		disp.setText(R.string.txt_soap_man);
	}
	
	public void onClickSoapLibrary(View v) {
		disp.setText(R.string.txt_soap_lib);
		soapSensor.registerListener(this);
		soapSensor.getTemperature();
	}
	
	private class SoapTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            //set up socket connection
        	//request temperature
        	//return result to UI thread?
        	return "now what?";
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
       }
    }

}
