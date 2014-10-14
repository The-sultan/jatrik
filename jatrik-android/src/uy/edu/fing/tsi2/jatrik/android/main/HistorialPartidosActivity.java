package uy.edu.fing.tsi2.jatrik.android.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import uy.edu.fing.tsi2.jatrik.android.extras.InfoPartido;
import uy.edu.fing.tsi2.jatrik.android.extras.InfoUsuario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jatrik_android.R;
import com.google.gson.Gson;

public class HistorialPartidosActivity extends ActionBarActivity {

	private HistorialAdapter adpt;

	private class ServiceHistorial extends AsyncTask <Void, Void, String> 	 {
	    private final ProgressDialog dialog = new ProgressDialog(HistorialPartidosActivity.this);
		
		
		protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
			InputStream in = entity.getContent();
			StringBuffer out = new StringBuffer();
			int n = 1;
			while (n>0) {
				byte[] b = new byte[4096];
				n =  in.read(b);
				if (n>0)
					out.append(new String(b, 0, n));
			}
			return out.toString();
			
		}

		@Override
		protected String doInBackground(Void... params) {
			Intent i = getIntent();
			String id = i.getStringExtra("idEquipo");
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpGet httpGet = new HttpGet("http://192.168.1.36:8080/jatrik-core-web/rest/equipos/" + id + "/historial");
			String text = null;
			try {
				HttpResponse response = httpClient.execute(httpGet, localContext);
				HttpEntity entity = response.getEntity();
				text = getASCIIContentFromEntity(entity);
			} catch (Exception e) {
				return e.getLocalizedMessage();
			}
			return text;
		}

		protected void onPostExecute(String results) {
		if (results!=null) {
			Gson gson = new Gson();
			HistorialPartidos historial = gson.fromJson(results, HistorialPartidos.class);
			dialog.dismiss();
			adpt.setPartidoList(historial.getPartidos());
			adpt.notifyDataSetChanged();
			}
		}

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_historial_partidos);
		Intent i = getIntent();
		String User = i.getStringExtra("User");
		TextView WelcomeMessage = (TextView)findViewById(R.id.textWelcome);
		WelcomeMessage.setText("Bienvenido, " + User );
		
		// Pido los partidos y los muestro
	    new ServiceHistorial().execute();
        adpt  = new HistorialAdapter(new ArrayList<InfoPartido>(), this);
        ListView lView = (ListView) findViewById(R.id.PartidosView);         
        lView.setAdapter(adpt);
	  }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.historial_partidos, menu);
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

}



