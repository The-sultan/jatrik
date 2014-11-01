package uy.edu.fing.tsi2.jatrik.common.payloads;

import java.util.List;

public class InfoEntrenamiento {
	
	private int idEquipo;
	private List<InfoEntrenamientoJugador> jugadores;

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public List<InfoEntrenamientoJugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<InfoEntrenamientoJugador> jugadores) {
		this.jugadores = jugadores;
	}	
	


	
}
