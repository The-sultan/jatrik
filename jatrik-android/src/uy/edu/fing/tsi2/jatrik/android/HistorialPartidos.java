package uy.edu.fing.tsi2.jatrik.android;

import java.util.List;

public class HistorialPartidos {
	
	private List<InfoPartido> partidos;
	
	public HistorialPartidos (List<InfoPartido> prt){
		partidos= prt;
	}
	
	public List<InfoPartido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<InfoPartido> partidos) {
		this.partidos = partidos;
	}
}

