package uy.edu.fing.tsi2.jatrik.android.main;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.android.extras.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.android.extras.InfoEvento;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventosAdapter extends ArrayAdapter<InfoEvento> {
	 
	private List<InfoEvento> eventoList;

	private Context context;
	 
	public EventosAdapter(List<InfoEvento> arrayList, Context ctx) {
	    super(ctx, R.layout.listview_eventos_item, arrayList);
	    this.eventoList = arrayList;
	    this.context = ctx;
	}
 
    public int getCount() {
        if (eventoList != null)
            return eventoList.size();
        return 0;
    }
 
    public InfoEvento getItem(int position) {
        if (eventoList != null)
            return eventoList.get(position);
        return null;
    }
 
    public long getItemId(int position) {
        if (eventoList != null)
            return eventoList.get(position).hashCode();
        return 0;
    }
	
	public View getView(int position, View convertView, ViewGroup parent) {
	     
	    // First let's verify the convertView is not null
	    if (convertView == null) {
	        // This a new view we inflate the new layout
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.listview_eventos_item, parent, false);
	    }
	    
        // Now we can fill the layout with the right values
	    TextView tv1 = (TextView) convertView.findViewById(R.id.minutoEvento);
        TextView tv2 = (TextView) convertView.findViewById(R.id.descripcionEvento);
        InfoEvento e = eventoList.get(position);

        tv1.setText(Integer.toString(e.getMinuto())); 
        tv2.setText(e.getDescripcion());   
  
        convertView.setBackgroundColor(Color.parseColor("#E4E4E4"));
		tv1.setTextColor(Color.parseColor("#3B3B3B"));
		tv2.setTextColor(Color.parseColor("#3B3B3B"));

	    return convertView;
	}
	
    public List<InfoEvento> geteventoList() {
        return eventoList;
    }
 
    public void seteventoList(List<InfoEvento> List) {
        this.eventoList = List;
    }
  
}