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

import uy.edu.fing.tsi2.jatrik.android.extras.HistorialPartidos;
import uy.edu.fing.tsi2.jatrik.android.extras.InfoPartido;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

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
			String id = ((DatosUsuario)HistorialPartidosActivity.this.getApplication()).getUsuario().getInfoEquipo().getId().toString();
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpGet httpGet = new HttpGet(((DatosUsuario)HistorialPartidosActivity.this.getApplication()).getUrlServicios() + "equipos/" + id + "/historial");
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
			((DatosUsuario)HistorialPartidosActivity.this.getApplication()).setPartidos(historial);
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
		
		// Pido los partidos y los muestro
	    new ServiceHistorial().execute();
        adpt  = new HistorialAdapter(new ArrayList<InfoPartido>(), this);
        adpt.setEquipo(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getNombre());
        ListView lView = (ListView) findViewById(R.id.PartidosView);         
        lView.setAdapter(adpt);

        lView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position,
                    long id) {
               
            	TextView TextViewId = (TextView) v.findViewById(R.id.idPartido);
                String idPartido = (String) TextViewId.getText();
            	
			    Intent intent = new Intent(HistorialPartidosActivity.this, DetallePartidoActivity.class);
			    intent.putExtra("idPartido", idPartido);
			    startActivity(intent);
            }
        });
        
        
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



