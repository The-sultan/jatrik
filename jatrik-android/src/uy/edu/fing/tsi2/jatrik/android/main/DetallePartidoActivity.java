package uy.edu.fing.tsi2.jatrik.android.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.google.gson.Gson;

import uy.edu.fing.tsi2.jatrik.android.extras.HistorialPartidos;
import uy.edu.fing.tsi2.jatrik.android.extras.InfoEvento;
import uy.edu.fing.tsi2.jatrik.android.extras.InfoPartido;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class DetallePartidoActivity extends ActionBarActivity {

	private EventosAdapter adpt;
	private InfoPartido partido;
	
	public InfoPartido getPartido() {
		return partido;
	}

	public void setPartido(InfoPartido partido) {
		this.partido = partido;
	}

	private class ServicePartido extends AsyncTask <Void, Void, String> 	 {
	    private final ProgressDialog dialog = new ProgressDialog(DetallePartidoActivity.this);
		
		
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
			Intent myIntent = getIntent();
			int idPartido = Integer.valueOf(myIntent.getStringExtra("idPartido"));
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpGet httpGet = new HttpGet(((DatosUsuario)DetallePartidoActivity.this.getApplication()).getUrlServicios() + "partidos/" + idPartido);
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
			partido = gson.fromJson(results, InfoPartido.class);
			dialog.dismiss();
			
			if (partido != null){
				
				TextView tvLocal = (TextView)findViewById(R.id.equipoLocalDetalle);
				TextView tvVisitante = (TextView)findViewById(R.id.equipoVisitanteDetalle);
				TextView tvGolesLocal = (TextView)findViewById(R.id.golesLocalDetalle);
				TextView tvGolesVisitante = (TextView)findViewById(R.id.golesVisitanteDetalle);	
				TextView tvEstado = (TextView)findViewById(R.id.estadoPartidoDetalle);	
				
				tvLocal.setText(partido.getEquipoLocal());
				tvVisitante.setText(partido.getEquipoVisitante());
				tvGolesLocal.setText(Integer.toString(partido.getGolesLocal()));
				tvGolesVisitante.setText(Integer.toString(partido.getGolesVisitante()));
				tvEstado.setText(partido.getEstado());
				
				int golesUsuario;
				int golesRival;
				
		        if (partido.getEquipoLocal().equals(((DatosUsuario)DetallePartidoActivity.this.getApplication()).getUsuario().getInfoEquipo().getNombre())) {
		            golesUsuario = partido.getGolesLocal();
		            golesRival = partido.getGolesVisitante(); 
		        } else {
		            golesUsuario = partido.getGolesVisitante();
		            golesRival = partido.getGolesLocal();    
		        }			
				
				if (partido.getEstado().equals("FINALIZADO")){
		        	
		        	tvEstado.setTextColor(Color.parseColor("#8D8D8D"));
		        	
			        if (golesUsuario == golesRival){
			        	tvLocal.setTextColor(Color.parseColor("#00005C"));
			        	tvVisitante.setTextColor(Color.parseColor("#00005C"));
			        	tvGolesLocal.setTextColor(Color.parseColor("#00005C"));
			        	tvGolesVisitante.setTextColor(Color.parseColor("#00005C"));
			        }
			        else
			        	if (golesUsuario > golesRival){
				        	tvLocal.setTextColor(Color.parseColor("#00561D"));
				        	tvVisitante.setTextColor(Color.parseColor("#00561D"));
				        	tvGolesLocal.setTextColor(Color.parseColor("#00561D"));
				        	tvGolesVisitante.setTextColor(Color.parseColor("#00561D"));
			        	}
			        	else
			        	{
			        		tvLocal.setTextColor(Color.parseColor("#8A0000"));
				        	tvVisitante.setTextColor(Color.parseColor("#8A0000"));
				        	tvGolesLocal.setTextColor(Color.parseColor("#8A0000"));
				        	tvGolesVisitante.setTextColor(Color.parseColor("#8A0000"));
			        	}
					}
		        else {
	        		tvLocal.setTextColor(Color.parseColor("#CBAC25"));
		        	tvVisitante.setTextColor(Color.parseColor("#CBAC25"));
		        	tvGolesLocal.setTextColor(Color.parseColor("#CBAC25"));
		        	tvGolesVisitante.setTextColor(Color.parseColor("#CBAC25"));
		        	tvEstado.setTextColor(Color.parseColor("#CBAC25"));
		        	tvEstado.setTypeface(null, Typeface.BOLD);
		        	
		        }
				
			}
			
			adpt.seteventoList(partido.getEventos());
			adpt.notifyDataSetChanged();
			
			}
		}

	}	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_partido);

		new ServicePartido().execute();
		
        adpt  = new EventosAdapter(new ArrayList<InfoEvento>(), this);
        ListView lView = (ListView) findViewById(R.id.EventosView);         
        lView.setAdapter(adpt);        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_partido, menu);
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
