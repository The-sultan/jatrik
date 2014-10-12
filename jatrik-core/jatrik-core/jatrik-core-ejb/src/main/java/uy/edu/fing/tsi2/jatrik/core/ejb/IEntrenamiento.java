package uy.edu.fing.tsi2.jatrik.core.ejb;

import java.util.Date;

import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumHabilidad;

public interface IEntrenamiento {
	public String entrenarEquipo(Long idEquipo, Date fechaEntrenamiento, EnumHabilidad modoEntrenamiento);
}
