package ch.ethz.inf.vs.a2.aenz.client;

import java.io.IOException;
import java.net.UnknownHostException;

import ch.ethz.inf.vs.a2.aenz.client.R;
import ch.ethz.inf.vs.a2.aenz.httpclient.ClientSocket;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HttpActivity extends Activity{
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
    }
    
    public void onClickRaw(View v) throws UnknownHostException, IOException {
//    	ClientSocket socket = new ClientSocket();
//    	socket.execute("");
//    	Toast.makeText(this, Boolean.valueOf(socket.isConnected()).toString(), Toast.LENGTH_LONG).show();
    }
    
    public void onClickLib(View v) {
    	
    }
    
    public void onClickRJson(View v) {
    	
    }
}
