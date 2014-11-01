package uy.edu.fing.tsi2.jatrik.android.main;

import uy.edu.fing.tsi2.jatrik.android.extras.HistorialPartidos;
import uy.edu.fing.tsi2.jatrik.android.extras.InfoUsuario;
import android.app.Application;

public class DatosUsuario extends Application{
	
	private InfoUsuario Usuario;
	private HistorialPartidos Partidos;
	private int ultimaNotificacion; 
	
	public int getUltimaNotificacion() {
		return ultimaNotificacion;
	}

	public void setUltimaNotificacion(int ultimaNotificacion) {
		this.ultimaNotificacion = ultimaNotificacion;
	}

	public HistorialPartidos getPartidos() {
		return Partidos;
	}

	public void setPartidos(HistorialPartidos partidos) {
		Partidos = partidos;
	}

	private String UrlServicios;

	public String getUrlServicios() {
		return UrlServicios;
	}

	public void setUrlServicios(String urlServicios) {
		UrlServicios = urlServicios;
	}

	public InfoUsuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(InfoUsuario usuario) {
		Usuario = usuario;
	}

}
