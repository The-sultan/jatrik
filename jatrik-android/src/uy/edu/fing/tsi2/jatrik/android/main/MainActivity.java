package uy.edu.fing.tsi2.jatrik.android.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	// Url del servicio REST que se invoca para el envio del identificador de
	// registro a la aplicaci�n jee
	public static final String URL_REGISTRO_ID = "http://192.168.3.126:8080/jatrik-core-web/rest/registration/id/add";
	// Se�a n�merica que se utiliza cuando se verifica la disponibilidad de los
	// google play services
	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	// Una simple Tag utilizada en los logs
	private static final String TAG = "Demo GCM";
	 
	public static final String EXTRA_MESSAGE = "message";
	// Clave que permite recuperar de las preferencias compartidas de la
	// aplicaci�n el dentificador de registro en GCM
	private static final String PROPERTY_REG_ID = "registration_id";
	// Clave que permite recuperar de las preferencias compartidas de la
	// aplicaci�n el dentificador de la versi�n de la aplicaci�n
	private static final String PROPERTY_APP_VERSION = "appVersion";
	// Identificador de la instancia del servicio de GCM al cual accedemos
	private static final String SENDER_ID = "572799473747";
	// Clase que da acceso a la api de GCM
	private GoogleCloudMessaging gcm;
	// Identificador de registro
	private String regid;
	// Contexto de la aplicaci�n
	private Context contexto;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);
	 
	  contexto = this;
	  // Se comprueba que Play Services APK estan disponibles, Si lo esta se
	  // proocede con el registro en GCM
	  if (chequearPlayServices()) {
	    gcm = GoogleCloudMessaging.getInstance(contexto);
	    // Se recupera el "registration Id" almacenado en caso que la
	    // aplicaci�n ya se hubiera registrado previamente
	    regid = obtenerIdentificadorDeRegistroAlmacenado();
	    // Si no se ha podido recuperar el id del registro procedemos a
	    // obtenerlo mediante el proceso de registro
	    if (regid.isEmpty()) {
	      // Se inicia el proceso de registro
	      registroEnSegundoPlano();
	    }
	  } else {
	    Log.i(TAG, "No valid Google Play Services APK found.");
	  }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		String User    = ((DatosUsuario)this.getApplication()).getUsuario().getNick();
		String Equipo  = ((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getNombre();
		String Estadio = ((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getEstadio().getNombre();
		String Fondos  = ((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getFondos().toString();
		
		TextView WelcomeMessage = (TextView)findViewById(R.id.textWelcome);
		TextView TeamMessage = (TextView)findViewById(R.id.textNombreEquipo);
		TextView StadiumMessage = (TextView)findViewById(R.id.textNombreEstadio);
		TextView MoneyMessage = (TextView)findViewById(R.id.textFondos);
		
		WelcomeMessage.setText("Bienvenido, " + User);
		TeamMessage.setText(Equipo);
		StadiumMessage.setText(Estadio);
		MoneyMessage.setText(Fondos);
		
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
	
	public void historialPartidos(View view) throws InterruptedException {
	    Intent intent = new Intent(MainActivity.this, HistorialPartidosActivity.class);
	    startActivity(intent);
	}
	
	public void plantillaEquipo(View view) throws InterruptedException {
	    Intent intent = new Intent(MainActivity.this, PlantillaActivity.class);
	    startActivity(intent);
	}
	
	
	//--------Procedimientos para notificaciones PUSH
	
	
	/**
	 * Este metodo comprueba si Google Play Services esta disponible, ya que
	 * este requiere que el terminal este asociado a una cuenta de google.Esta
	 * verificaci�n es necesaria porque no todos los dispositivos Android estan
	 * asociados a una cuenta de Google ni usan sus servicios, por ejemplo, el
	 * Kindle fire de Amazon, que es una tablet Android pero no requiere de una
	 * cuenta de Google.
	 *
	 * @return Indica si Google Play Services esta disponible.
	 */
	 private boolean chequearPlayServices() {
		  int resultCode = GooglePlayServicesUtil
		      .isGooglePlayServicesAvailable(contexto);
		  if (resultCode != ConnectionResult.SUCCESS) {
		    if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
		      GooglePlayServicesUtil.getErrorDialog(resultCode, this,
		          PLAY_SERVICES_RESOLUTION_REQUEST).show();
		    } else {
		      Log.i(TAG, "Dispositivo no soportado.");
		      finish();
		    }
		    return false;
		  }
		  return true;
		}
	 
	 /**
	   * Metodo que recupera el registration ID que fue almacenado la ultima vez
	   * que la aplicaci�n se registro, En caso que la aplicaci�n este
	   * desactualizada o no se haya registrado previamente no se recuperara
	   * ning�n registration ID
	   *
	   * @return identificador del registro, o vacio("") si no existe o esta
	   *         desactualizado dicho registro
	   */
	  private String obtenerIdentificadorDeRegistroAlmacenado() {
	    final SharedPreferences prefs = getPreferenciasCompartidas();
	    String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	    if (registrationId.isEmpty()) {
	      Log.i(TAG, "Registration not found.");
	      return "";
	    }
	    // Comprueba si la aplicaci�n esta actualizada
	    int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION,
	        Integer.MIN_VALUE);
	    int currentVersion = getVersionDeLaAplicacion();
	    if (registeredVersion != currentVersion) {
	      Log.i(TAG, "App version changed.");
	      return "";
	    }
	    return registrationId;
	  }
	 
	/**
	   * Metodo que sirve para recupera las preferencias compartidas en modo privado
	   *
	   * @return Application's {@code SharedPreferences}.
	   */
	  private SharedPreferences getPreferenciasCompartidas() {
	    return getSharedPreferences(MainActivity.class.getSimpleName(),
	        Context.MODE_PRIVATE);
	  }
	 
	  /**
	   * Recupera la versi�n aplicaci�n que identifica a cada una de las
	   * actualizaciones de la misma.
	   *
	   * @return La versi�n del codigo de la aplicaci�n
	   */
	  private int getVersionDeLaAplicacion() {
	    try {
	      PackageInfo packageInfo = contexto.getPackageManager()
	          .getPackageInfo(contexto.getPackageName(), 0);
	      return packageInfo.versionCode;
	    } catch (NameNotFoundException e) {
	      // should never happen
	      throw new RuntimeException("Could not get package name: " + e);
	    }
	  }
	  
	  /**
	   * En este m�todo se procede al registro de la aplicaci�n obteniendo el
	   * identificador de registro que se almacena en la tarjeta de memoria para
	   * no tener que repetir el mismo proceso la pr�xima vez. Adicionalmente se
	   * env�a el identificador de registro al a la aplicaci�n jee , invocando un
	   * servicio REST.
	   */
	  private void registroEnSegundoPlano() {
	    new AsyncTask<Object, Object, Object>() {
	      @Override
	      protected void onPostExecute(final Object result) {
	        Log.i(TAG, result.toString());
	      }
	   
	      @Override
	      protected String doInBackground(final Object... params) {
	        String msg = "";
	        try {
	          if (gcm == null) {
	            gcm = GoogleCloudMessaging.getInstance(contexto);
	          }
	          // En este metodo se invoca al servicio de registro de los
	          // servicios GCM
	          regid = gcm.register(SENDER_ID);
	          msg = "Dispositivo3 registrado, registration ID=" + regid;
	          Log.i(TAG, msg);
	          // Una vez se tiene el identificador de registro se manda a
	          // la aplicacion jee
	          // ya que para que esta envie el mensaje de la notificaci�n
	          // a los servidores
	          // de GCM es necesario dicho identificador
	          enviarIdentificadorRegistroALaAplicacionJ2ee();
	          // Se persiste el identificador de registro para que no sea
	          // necesario repetir el proceso de
	          // registro la proxima vez
	          almacenarElIdentificadorDeRegistro(regid);
	        } catch (Exception e) {
	          msg = "Error :" + e.getMessage();
	          e.printStackTrace();
	        }
	        return msg;
	      }
	   
	    }.execute(this, null, null);
	  }
	   
	  /**
	   * Se almacena el identificador de registro de "Google Cloud Message" y la
	   * versi�n de la aplicaci�n
	   *
	   * @param regId identificador de registro en GCM
	   */
	  private void almacenarElIdentificadorDeRegistro(String regId) {
	    final SharedPreferences prefs = getPreferenciasCompartidas();
	    int appVersion = getVersionDeLaAplicacion();
	    Log.i(TAG, "Saving regId on app version " + appVersion);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putString(PROPERTY_REG_ID, regId);
	    editor.putInt(PROPERTY_APP_VERSION, appVersion);
	    editor.commit();
	  }
	  
	  /**
	   * Se env�a el identificador de registro de GCM mediante la invocaci�n de un
	   * servicio REST por el m�todo POST, pas�ndole por par�metro un objeto json
	   * que envuelve dicho identificador
	   *
	   * @param url
	   *            URL del servicio REST al cual invocar
	   * @param json
	   *            Objeto json que contiene el identificador de registro a enviar
	   * @return Devuelve un objeto json que contiene un mensaje de confirmaci�n
	   *         del envio del identificador del registro
	   * @throws Exception
	   */
	   
	  private void enviarIdentificadorRegistroALaAplicacionJ2ee()
	      throws Exception {
	    JSONObject requestRegistrationId = new JSONObject();
	    requestRegistrationId.put("registrationId", regid);
	    requestRegistrationId.put("idUsuario", ((DatosUsuario)MainActivity.this.getApplication()).getUsuario().getId().toString());
	    BufferedReader in = null;
	    try {
	      HttpClient client = new DefaultHttpClient();
	      HttpPost httpPost = new HttpPost();
	      httpPost.setURI(new URI(URL_REGISTRO_ID));
	      httpPost.setEntity(new StringEntity(requestRegistrationId
	          .toString(), "UTF-8"));
	      httpPost.setHeader("content-type", "application/json");
	      HttpResponse response = client.execute(httpPost);
	      InputStreamReader lectura = new InputStreamReader(response
	          .getEntity().getContent());
	      in = new BufferedReader(lectura);
	      StringBuffer sb = new StringBuffer("");
	      String line = "";
	      while ((line = in.readLine()) != null) {
	        sb.append(line);
	      }
	      in.close();
	      Log.i("INFO", sb.toString());
	    } catch (Exception e) {
	      Log.e("ERROR", e.getMessage(), e);
	    } finally {
	      if (in != null) {
	        try {
	          in.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	  }
}
