package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;



public interface IEMPartidos {

	void delete(Partido partido);

	Partido update(Partido partido);

	Partido find(Long id);

	List<Partido> findAll();
	
	List<Partido> findPartidosDeEquipo(Long idEquipo);
}