package uy.edu.fing.tsi2.jatrik.android.main;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.android.extras.InfoPartido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jatrik_android.R;

public class HistorialAdapter extends ArrayAdapter<InfoPartido> {
	 
	private List<InfoPartido> partidoList;
	private Context context;
	 
	public HistorialAdapter(List<InfoPartido> partidoList, Context ctx) {
	    super(ctx, R.layout.listview_historial_item, partidoList);
	    this.partidoList = partidoList;
	    this.context = ctx;
	}
 
    public int getCount() {
        if (partidoList != null)
            return partidoList.size();
        return 0;
    }
 
    public InfoPartido getItem(int position) {
        if (partidoList != null)
            return partidoList.get(position);
        return null;
    }
 
    public long getItemId(int position) {
        if (partidoList != null)
            return partidoList.get(position).hashCode();
        return 0;
    }
	
	public View getView(int position, View convertView, ViewGroup parent) {
	     
	    // First let's verify the convertView is not null
	    if (convertView == null) {
	        // This a new view we inflate the new layout
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.listview_historial_item, parent, false);
	    }
	        // Now we can fill the layout with the right values
	        TextView tv1 = (TextView) convertView.findViewById(R.id.equipoLocal);
	        TextView tv2 = (TextView) convertView.findViewById(R.id.golesLocal);
	        TextView tv3 = (TextView) convertView.findViewById(R.id.golesVisitante);
	        TextView tv4 = (TextView) convertView.findViewById(R.id.equipoVisitante);
	        InfoPartido p = partidoList.get(position);
	 
	        tv1.setText(p.getEquipoLocal());   
	        tv2.setText(Integer.toString(p.getGolesLocal()));   
	        tv3.setText(Integer.toString(p.getGolesVisitante()));   	        
	        tv4.setText(p.getEquipoVisitante());  
	     
	    return convertView;
	}
	
    public List<InfoPartido> getPartidoList() {
        return partidoList;
    }
 
    public void setPartidoList(List<InfoPartido> List) {
        this.partidoList = List;
    }
  
}