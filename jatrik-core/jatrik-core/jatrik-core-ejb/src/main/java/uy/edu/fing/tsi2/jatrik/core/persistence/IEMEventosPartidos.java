package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.eventos.EventoPartido;



public interface IEMEventosPartidos {


	void delete(EventoPartido eventoPartido);

	EventoPartido update(EventoPartido eventoPartido);

	EventoPartido find(Long id);

	List<EventoPartido> findAll();

	EventoPartido add(EventoPartido eventoPartido);
	
	EventoPartido findByName(String nombre);
	
	List<EventoPartido> findByPartido(Long partidoId);
	
	
}