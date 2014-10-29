package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;
import java.util.Set;

import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoJugador;



public interface IEMJugadores {


	void delete(Jugador jugador);

	Jugador update(Jugador jugador);

	Jugador find(Long id);

	List<Jugador> findAll();

	Jugador add(Jugador jugador);
	
	List<Jugador> findJugadoresLibres();
	
	List<Jugador> findJugadoresPuestoLibres(EnumPuestoJugador puesto);
	
	Set<Jugador> findJugadoresdelEquipo(Long idEquipo);
}