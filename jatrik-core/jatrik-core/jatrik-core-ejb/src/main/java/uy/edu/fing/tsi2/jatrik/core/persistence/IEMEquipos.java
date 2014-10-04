package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;



public interface IEMEquipos {


	void delete(Equipo equipo);

	Equipo update(Equipo equipo);

	Equipo find(Long id);

	List<Equipo> findAll();

	Equipo add(Equipo equipo);
	
	Equipo findEquipoLibre(); 
}