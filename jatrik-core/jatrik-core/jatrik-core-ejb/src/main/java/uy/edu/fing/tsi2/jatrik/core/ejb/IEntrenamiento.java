package uy.edu.fing.tsi2.jatrik.core.ejb;

import java.util.Date;

import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumHabilidad;

public interface IEntrenamiento {
	public boolean puedeEntrenar(Long idEquipo, Date fechaEntrenamiento);
	public void entrenarJugador(Long idJugador, EnumHabilidad modoEntrenamiento);
	public void setFechaEntrenamiento(Long idEquipo, Date fecha);
}
