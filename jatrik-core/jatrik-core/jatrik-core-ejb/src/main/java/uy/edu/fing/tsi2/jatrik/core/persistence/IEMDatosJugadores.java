package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.DatosJugador;



public interface IEMDatosJugadores {


	void delete(DatosJugador jugador);

	DatosJugador update(DatosJugador jugador);

	DatosJugador find(Long id);

	List<DatosJugador> findAll();

	DatosJugador add(DatosJugador jugador);
	
	
}