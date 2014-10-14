package uy.edu.fing.tsi2.jatrik.android.main;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.android.extras.InfoPartido;

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

