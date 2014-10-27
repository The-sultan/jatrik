package uy.edu.fing.tsi2.jatrik.android.main;

import java.util.ArrayList;
import java.util.List;

import uy.edu.fing.tsi2.jatrik.android.extras.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.android.extras.InfoHabilidad;
import uy.edu.fing.tsi2.jatrik.android.extras.InfoJugador;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HabilidadesAdapter extends ArrayAdapter<InfoHabilidad> {
	 
	private List<InfoHabilidad> habilidadList;
	private Context context;
	 
	public HabilidadesAdapter(List<InfoHabilidad> arrayList, Context ctx) {
	    super(ctx, R.layout.listview_habilidades_item, arrayList);
	    this.habilidadList = arrayList;
	    this.context = ctx;
	}
 
    public int getCount() {
        if (habilidadList != null)
            return habilidadList.size();
        return 0;
    }
 
    public InfoHabilidad getItem(int position) {
        if (habilidadList != null)
            return habilidadList.get(position);
        return null;
    }
 
    public long getItemId(int position) {
        if (habilidadList != null)
            return habilidadList.get(position).hashCode();
        return 0;
    }
	
	public View getView(int position, View convertView, ViewGroup parent) {
	     
	    // First let's verify the convertView is not null
	    if (convertView == null) {
	        // This a new view we inflate the new layout
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.listview_habilidades_item, parent, false);
	    }
	    
        // Now we can fill the layout with the right values
	    TextView tv1 = (TextView) convertView.findViewById(R.id.nombreHabilidad);
        TextView tv2 = (TextView) convertView.findViewById(R.id.valorHabilidad);
        InfoHabilidad h = habilidadList.get(position);

        tv1.setText(h.getNombre() + ":");
        tv2.setText(Integer.toString(h.getValor())); 	      
        
	    return convertView;
	}
	
    public List<InfoHabilidad> getJugadorList() {
        return habilidadList;
    }
 
    public void setJugadorList(List<InfoHabilidad> List) {
        this.habilidadList = List;
    }
  
}