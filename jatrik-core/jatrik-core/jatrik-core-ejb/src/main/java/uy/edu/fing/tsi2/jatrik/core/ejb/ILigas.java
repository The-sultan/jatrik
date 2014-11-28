package uy.edu.fing.tsi2.jatrik.core.ejb;

import java.util.List;
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
        
        void deleteLiga(Liga liga);
        
        List<Liga> obtenerLigasEnCurso();
        
        List<Liga> obtenerLigasVigentes();
        
        Liga updateLiga(Liga liga);
        
        void inscribirEquipoEnLiga(Equipo equipo, Liga liga);
        
        Liga crearLiga(String descripcion);
        
              
        
    
}
