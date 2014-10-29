package uy.edu.fing.tsi2.jatrik.android.main;

import java.util.ArrayList;

import uy.edu.fing.tsi2.jatrik.android.extras.InfoJugador;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class PlantillaActivity extends ActionBarActivity {

	private PlantillaAdapter adpt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plantilla);
		
		ArrayList<InfoJugador> jugadores = new ArrayList<InfoJugador>();
		jugadores.add(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getGolero());
		jugadores.addAll(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getDefensas());
		jugadores.addAll(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getMediocampistas());
		jugadores.addAll(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getDelanteros());
		jugadores.addAll(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getSuplentes());
		jugadores.addAll(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo().getReservas());
		
		adpt  = new PlantillaAdapter(jugadores, this);
		adpt.setEquipo(((DatosUsuario)this.getApplication()).getUsuario().getInfoEquipo());
        ListView lView = (ListView) findViewById(R.id.PlantillaView);         
        lView.setAdapter(adpt);

        lView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position,
                    long id) {
                TextView TextViewId = (TextView) v.findViewById(R.id.idJugador);
                String idJugador = (String) TextViewId.getText();
       
			    Intent intent = new Intent(PlantillaActivity.this, DetalleJugadorActivity.class);
			    intent.putExtra("idJugador", idJugador);
			    startActivity(intent);    
            }
        });
	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.plantilla, menu);
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
