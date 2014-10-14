package uy.edu.fing.tsi2.jatrik.android.main;


import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

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

import com.example.jatrik_android.R;
import com.google.gson.Gson;

public class MainActivity extends ActionBarActivity {

	private InfoUsuario usuarioValido;

	public InfoUsuario getUsuarioValido() {
		return usuarioValido;
	}

	public void setUsuarioValido(InfoUsuario usuarioValido) {
		this.usuarioValido = usuarioValido;
	}

	private class ServiceLogin extends AsyncTask <Void, Void, String> 	 {
	    private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
		
		
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
			
			String User = ((EditText)findViewById(R.id.editTextUsuario)).getText().toString();
			String Pass = ((EditText)findViewById(R.id.editTextClave)).getText().toString();
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpGet httpGet = new HttpGet("http://192.168.1.36:8080/jatrik-core-web/rest/login/?nick=" + User + "&password=" + Pass);
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
			usuarioValido = null;
			if (results!=null) {
				try{
					Gson gson = new Gson();
					usuarioValido = gson.fromJson(results, InfoUsuario.class);
				}
				catch(Exception e) {
					// No se hace nada, lo puse para prevenir que explote, el usuario queda en null y no permite el acceso
				}
			}
			dialog.dismiss();
		}

	}	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		if (usuarioValido != null){
		    Intent intent = new Intent(this, HistorialPartidosActivity.class);
		    intent.putExtra("User", usuarioValido.getNick());
		    intent.putExtra("idEquipo", usuarioValido.getInfoEquipo().getId().toString());
		    startActivity(intent);			
		} else {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
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
	}
}
