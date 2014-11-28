package uy.edu.fing.tsi2.jatrik.core.ejb;

import java.util.List;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Liga;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.domain.RelLigaEquipo;
import uy.edu.fing.tsi2.jatrik.core.domain.RelLigaPartido;


public interface ILigas {

	Long creandoMiLiga(String nombre);
	
	void initLigas();
        
        Liga find(Long id);
        
        void actualizarTablaPosiciones(Partido partido);
        
        Liga obtenerLigaEquipo(Long idEquipo);
	
        void crearFixtureConEtapas(Liga liga);
        
        Liga obtenerLigaPartido(Long idPartido);
        
        void deleteLiga(Liga liga);
        
        List<Liga> obtenerLigasEnCurso();
        
        List<Liga> obtenerLigasVigentes();
        
        Liga updateLiga(Liga liga);
        
        void inscribirEquipoEnLiga(Equipo equipo, Liga liga);
        
        Liga crearLiga(String descripcion);
        
        List<RelLigaEquipo> obtenerTablaPosiciones(Liga liga);
        
        List<RelLigaPartido> obtenerFixture(Liga liga);
        
        //Crea equipos con valores generales y una liga que los contiene
    //Solo la usariamos si quisieramos automatizar la generacion de liga y equipos
        Long initLigasEquipos(); //Devuelve el id de la liga creada
        
    
}
