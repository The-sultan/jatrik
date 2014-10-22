package uy.edu.fing.tsi2.jatrik.android.main;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.android.extras.InfoPartido;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HistorialAdapter extends ArrayAdapter<InfoPartido> {
	 
	private List<InfoPartido> partidoList;
	private String Equipo; 
	
	public String getEquipo() {
		return Equipo;
	}

	public void setEquipo(String equipo) {
		Equipo = equipo;
	}

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
        TextView tv5 = (TextView) convertView.findViewById(R.id.estado);
        InfoPartido p = partidoList.get(position);

        tv1.setText(p.getEquipoLocal());   
        tv2.setText(Integer.toString(p.getGolesLocal()));   
        tv3.setText(Integer.toString(p.getGolesVisitante()));   	        
        tv4.setText(p.getEquipoVisitante());  
        tv5.setText(p.getEstado());
        
        int golesUsuario;
        int golesRival;
       
        if (p.getEquipoLocal().equals(this.Equipo)) {
            golesUsuario = p.getGolesLocal();
            golesRival = p.getGolesVisitante(); 
        } else {
            golesUsuario = p.getGolesVisitante();
            golesRival = p.getGolesLocal();    
        }
        
        if (p.getEstado().equals("Finalizado")){
        	
        	tv5.setTextColor(Color.parseColor("#8D8D8D"));
        	
	        if (golesUsuario == golesRival){
	        	convertView.setBackgroundColor(Color.parseColor("#DDE0FF"));
	        	tv1.setTextColor(Color.parseColor("#00005C"));
	        	tv2.setTextColor(Color.parseColor("#00005C"));
	        	tv3.setTextColor(Color.parseColor("#00005C"));
	        	tv4.setTextColor(Color.parseColor("#00005C"));
	        }
	        else
	        	if (golesUsuario > golesRival){
	        		convertView.setBackgroundColor(Color.parseColor("#E3FFE5"));
	            	tv1.setTextColor(Color.parseColor("#00561D"));
	            	tv2.setTextColor(Color.parseColor("#00561D"));
	            	tv3.setTextColor(Color.parseColor("#00561D"));
	            	tv4.setTextColor(Color.parseColor("#00561D"));
	        	}
	        	else
	        	{
	        		convertView.setBackgroundColor(Color.parseColor("#FFE3E3"));
	            	tv1.setTextColor(Color.parseColor("#8A0000"));
	            	tv2.setTextColor(Color.parseColor("#8A0000"));
	            	tv3.setTextColor(Color.parseColor("#8A0000"));
	            	tv4.setTextColor(Color.parseColor("#8A0000"));
	        	}
			}
        else {
        	convertView.setBackgroundColor(Color.parseColor("#FFFFA6"));
        	tv1.setTextColor(Color.parseColor("#CBAC25"));
        	tv2.setTextColor(Color.parseColor("#CBAC25"));
        	tv3.setTextColor(Color.parseColor("#CBAC25"));
        	tv4.setTextColor(Color.parseColor("#CBAC25"));
        	tv5.setTextColor(Color.parseColor("#CBAC25"));
        	tv5.setTypeface(null, Typeface.BOLD);
        	
        }
        
	    return convertView;
	}
	
    public List<InfoPartido> getPartidoList() {
        return partidoList;
    }
 
    public void setPartidoList(List<InfoPartido> List) {
        this.partidoList = List;
    }
  
}