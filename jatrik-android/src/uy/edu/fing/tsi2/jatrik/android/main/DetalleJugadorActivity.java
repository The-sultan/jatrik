package uy.edu.fing.tsi2.jatrik.android.main;

import java.util.ArrayList;
import java.util.List;

import uy.edu.fing.tsi2.jatrik.android.extras.InfoJugador;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetalleJugadorActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_jugador);

		TextView tvNombre = (TextView)findViewById(R.id.NombreJugador);
		TextView tvNumero = (TextView)findViewById(R.id.NumeroJugador);
		TextView tvEdad = (TextView)findViewById(R.id.EdadJugador);
		TextView tvAltura = (TextView)findViewById(R.id.AlturaJugador);		
		TextView tvPeso = (TextView)findViewById(R.id.PesoJugador);
		TextView tvPuesto = (TextView)findViewById(R.id.PuestoJugador);			
		
		
		Intent myIntent = getIntent();
		int idJugador = Integer.valueOf(myIntent.getStringExtra("idJugador"));
		
		ArrayList<InfoJugador> jugadores = new ArrayList<InfoJugador>();
		jugadores.add(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getGolero());
		jugadores.addAll(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getDefensas());
		jugadores.addAll(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getMediocampistas());
		jugadores.addAll(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getDelanteros());
		jugadores.addAll(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getSuplentes());
		jugadores.addAll(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getReservas());
		for (InfoJugador j : jugadores) {
			if (j.getId() == idJugador){
				tvNombre.setText(j.getNombre());
				tvNumero.setText(Integer.toString(j.getNroCamiseta()));
				tvEdad.setText(Integer.toString(j.getEdad()));
				tvAltura.setText(Double.toString(j.getAltura()));
				tvPeso.setText(Double.toString(j.getPeso()));
				tvPuesto.setText(j.getPuesto());
				break;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_jugador, menu);
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
