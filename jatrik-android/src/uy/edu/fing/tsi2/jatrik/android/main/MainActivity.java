package uy.edu.fing.tsi2.jatrik.android.main;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
}
