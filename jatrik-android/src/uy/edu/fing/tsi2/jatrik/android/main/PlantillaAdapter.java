package uy.edu.fing.tsi2.jatrik.android.main;

import java.util.ArrayList;
import java.util.List;

import uy.edu.fing.tsi2.jatrik.android.extras.InfoJugador;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlantillaAdapter extends ArrayAdapter<InfoJugador> {
	 
	private List<InfoJugador> jugadorList;
	private Context context;
	 
	public PlantillaAdapter(List<InfoJugador> arrayList, Context ctx) {
	    super(ctx, R.layout.listview_plantilla_item, arrayList);
	    this.jugadorList = arrayList;
	    this.context = ctx;
	}
 
    public int getCount() {
        if (jugadorList != null)
            return jugadorList.size();
        return 0;
    }
 
    public InfoJugador getItem(int position) {
        if (jugadorList != null)
            return jugadorList.get(position);
        return null;
    }
 
    public long getItemId(int position) {
        if (jugadorList != null)
            return jugadorList.get(position).hashCode();
        return 0;
    }
	
	public View getView(int position, View convertView, ViewGroup parent) {
	     
	    // First let's verify the convertView is not null
	    if (convertView == null) {
	        // This a new view we inflate the new layout
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.listview_plantilla_item, parent, false);
	    }
	    
        // Now we can fill the layout with the right values
	    TextView tv1 = (TextView) convertView.findViewById(R.id.numeroJugador);
        TextView tv2 = (TextView) convertView.findViewById(R.id.nombreJugador);
        TextView tv3 = (TextView) convertView.findViewById(R.id.posicionJugador);
        TextView tv4 = (TextView) convertView.findViewById(R.id.idJugador);
        InfoJugador j = jugadorList.get(position);

        tv1.setText(Integer.toString(j.getNroCamiseta())); 
        tv2.setText(j.getNombre());   
        tv3.setText(j.getPuesto()); 
        tv4.setText(Long.toString(j.getId()));

		convertView.setBackgroundColor(Color.parseColor("#E4E4E4"));
		tv1.setTextColor(Color.parseColor("#3B3B3B"));
		tv2.setTextColor(Color.parseColor("#3B3B3B"));
		tv3.setTextColor(Color.parseColor("#9F9F9F"));
		tv4.setTextColor(Color.parseColor("#E4E4E4"));
	      
        
	    return convertView;
	}
	
    public List<InfoJugador> getJugadorList() {
        return jugadorList;
    }
 
    public void setJugadorList(List<InfoJugador> List) {
        this.jugadorList = List;
    }
  
}