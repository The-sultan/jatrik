package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.Evento;



public interface IEMEventos {


	void delete(Evento jugador);

	Evento update(Evento jugador);

	Evento find(Long id);

	List<Evento> findAll();

	Evento add(Evento jugador);
	
	Evento findByName(String nombre);
	
	
}