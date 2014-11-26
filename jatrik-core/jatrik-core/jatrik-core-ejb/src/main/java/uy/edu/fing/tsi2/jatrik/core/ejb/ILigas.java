package uy.edu.fing.tsi2.jatrik.core.ejb;

import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Liga;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;


public interface ILigas {

	Long creandoMiLiga(String nombre);
	
	void initLigas();
        
        Liga find(Long id);
        
        void actualizarTablaPosiciones(Partido partido);
        
        Liga obtenerLigaEquipo(Equipo equipo);
	
        void crearFixtureConEtapas(Liga liga);
        
        Liga obtenerLigaPartido(Partido partido);
    
}
