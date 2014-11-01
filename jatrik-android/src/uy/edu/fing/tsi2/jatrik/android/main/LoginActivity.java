package uy.edu.fing.tsi2.jatrik.android.main;


import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import uy.edu.fing.tsi2.jatrik.android.extras.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.android.extras.InfoUsuario;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

public class LoginActivity extends ActionBarActivity {

	private class ServiceLogin extends AsyncTask <Void, Void, InfoUsuario> 	 {
	    private final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
		
		
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
		protected InfoUsuario doInBackground(Void... params) {
			
			InfoUsuario infoUsuario = new InfoUsuario();
			InfoEquipo infoEquipo = new InfoEquipo();
			Gson gson = new Gson();
			
			String User = ((EditText)findViewById(R.id.editTextUsuario)).getText().toString();
			String Pass = ((EditText)findViewById(R.id.editTextClave)).getText().toString();
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpGet httpGet1 = new HttpGet(((DatosUsuario)LoginActivity.this.getApplication()).getUrlServicios() + "login/?nick=" + User + "&password=" + Pass);
			String text = null;
			HttpParams httpParameters = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, 30000);
			HttpConnectionParams.setSoTimeout(httpParameters, 30000);
			try {
				HttpResponse response = httpClient.execute(httpGet1, localContext);
				HttpEntity entity = response.getEntity();
				text = getASCIIContentFromEntity(entity);
				if (text!=null) {
					infoUsuario = gson.fromJson(text, InfoUsuario.class);
				}
				if (infoUsuario!=null){
					HttpGet httpGet2 = new HttpGet(((DatosUsuario)LoginActivity.this.getApplication()).getUrlServicios() + "equipos/" + infoUsuario.getInfoEquipo().getId().toString());
					response = httpClient.execute(httpGet2, localContext);
					entity = response.getEntity();
					text = getASCIIContentFromEntity(entity);
					infoEquipo = gson.fromJson(text, InfoEquipo.class);		
					if (infoEquipo!=null){
						infoUsuario.setInfoEquipo(infoEquipo);
					}
				}
			} catch (Exception e) {
				return null;
			}
			return infoUsuario;
		}

		protected void onPostExecute(InfoUsuario infoUsuario) {
			((DatosUsuario)LoginActivity.this.getApplication()).setUsuario(infoUsuario);
			if (infoUsuario != null){
			    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			    startActivity(intent);			
			} else {
	            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(LoginActivity.this);
	            dlgAlert.setMessage("Usuario o contraseña inválida");
	            dlgAlert.setTitle("Error");
	            dlgAlert.setPositiveButton("OK", null);
	            dlgAlert.setCancelable(true);
	            dlgAlert.create().show();
	            dlgAlert.setPositiveButton("Ok",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) {

	                }
	            });		
			}
			dialog.dismiss();
		}

	}	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		((DatosUsuario)LoginActivity.this.getApplication()).setUrlServicios("http://192.168.1.34:8080/jatrik-core-web/rest/");
		((DatosUsuario)LoginActivity.this.getApplication()).setUltimaNotificacion(1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	
	public void LogIn(View view) throws InterruptedException {
		new ServiceLogin().execute();
	}
}
