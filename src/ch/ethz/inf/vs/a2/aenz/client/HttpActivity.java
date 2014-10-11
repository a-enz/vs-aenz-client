package ch.ethz.inf.vs.a2.aenz.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import ch.ethz.inf.vs.a2.aenz.client.R;
import ch.ethz.inf.vs.a2.aenz.http.Requester;
import ch.ethz.inf.vs.a2.aenz.httpclient.ClientRequester2;
import ch.ethz.inf.vs.a2.aenz.httpclient.ClientSocket;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class HttpActivity extends Activity{
	
	private TextView responseTxt;
	private final String TAG = "HttpActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        
        responseTxt = (TextView) findViewById(R.id.textViewResponse);
    }
    
    public void onClickRaw(View v) {
    	HttpTask request = new HttpTask();
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
    	HttpTask2 request = new HttpTask2();
    	request.execute(new String[]{});
    	try {
			responseTxt.setText("Response Lib: " + request.get());
		} catch (InterruptedException e) {
			Log.d(TAG, "InterruptedException");
			e.printStackTrace();
		} catch (ExecutionException e) {
			Log.d(TAG, "ExecutionException");
			e.printStackTrace();
		}
    }
    
    public void onClickJson(View v) {
    	
    }
    
    @Override
    public void onStop(){
    	super.onStop();
    }
    
    private class HttpTask extends AsyncTask<String, String, String> {

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
    
    private class HttpTask2 extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			ClientRequester2 requester2 = new ClientRequester2();
			return requester2.executeRequest();
		}
    }

}
