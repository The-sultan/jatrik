package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;

public interface IEMPartidos {

	Partido add(Partido partido);

	Partido update(Partido partido);

	void delete(Partido partido);

	Partido find(Long id);

	List<Partido> findAll();
}
