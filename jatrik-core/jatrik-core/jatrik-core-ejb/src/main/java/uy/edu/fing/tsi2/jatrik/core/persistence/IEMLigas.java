package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;

import uy.edu.fing.tsi2.jatrik.core.domain.Liga;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;



public interface IEMLigas {


	void delete(Liga liga);

	Liga update(Liga liga);

	Liga find(Long id);

	List<Liga> findAll();

	Liga add(Liga liga);
	
        Liga findLigaByEquipo(Equipo equipo);
        
        Liga findLigaByPartido(Partido partido);
        
        List<Liga> obtenerLigasEnCurso();
        
        List<Liga> obtenerLigasNoIniciados();
        
        
		
}