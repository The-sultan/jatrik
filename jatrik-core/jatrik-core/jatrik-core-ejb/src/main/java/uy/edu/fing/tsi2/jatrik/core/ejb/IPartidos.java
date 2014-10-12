package uy.edu.fing.tsi2.jatrik.core.ejb;

import java.util.Date;
import java.util.List;
import java.util.Set;

import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEstadoPartido;

public interface IPartidos {

	List<Partido> obtenerPartidos(Long idEquipo);
	
	List<Partido> obtenerPartidos(Date fechaDesde, Date fechaHasta,Set<EnumEstadoPartido> estados);
	
	Evento encontrarEvento(Long id);
}
