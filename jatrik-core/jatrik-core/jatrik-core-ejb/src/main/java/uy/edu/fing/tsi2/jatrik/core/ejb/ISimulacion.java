package uy.edu.fing.tsi2.jatrik.core.ejb;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;

public interface ISimulacion {

	void simularEvento(Partido partido);
	
	void simularPartido(Long idPartido);
}
