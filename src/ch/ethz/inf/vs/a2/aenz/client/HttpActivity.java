package ch.ethz.inf.vs.a2.aenz.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import ch.ethz.inf.vs.a2.aenz.client.R;
import ch.ethz.inf.vs.a2.aenz.httpclient.ClientSocket;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


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
			responseTxt.setText("Response" + request.get());
		} catch (InterruptedException e) {
			Log.d(TAG, "InterruptedException");
			e.printStackTrace();
		} catch (ExecutionException e) {
			Log.d(TAG, "ExecutionException");
			e.printStackTrace();
		}
    }
    
    public void onClickLib(View v) {
    	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Host not found";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Sth wrong with IO";
			}
		}
    }

}
