package uy.edu.fing.tsi2.jatrik.android.main;

import uy.edu.fing.tsi2.jatrik.android.extras.InfoUsuario;
import android.app.Application;

public class DatosUsuario extends Application{
	
	private InfoUsuario Usuario;

	public InfoUsuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(InfoUsuario usuario) {
		Usuario = usuario;
	}

}