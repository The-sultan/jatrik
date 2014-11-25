package uy.edu.fing.tsi2.jatrik.core.ejb;

import uy.edu.fing.tsi2.jatrik.core.domain.Liga;


public interface ILigas {

	Long creandoMiLiga(String nombre);
	
	void initLigas();
        
        Liga find(Long id);
}
