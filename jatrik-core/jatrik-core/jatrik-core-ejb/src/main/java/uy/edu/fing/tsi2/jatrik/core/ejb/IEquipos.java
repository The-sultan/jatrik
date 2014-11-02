package uy.edu.fing.tsi2.jatrik.core.ejb;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoFormacion;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Formacion;

public interface IEquipos {
	
	Equipo find(Long id);
	
	Formacion getFormacionEstandar(Long id);
	
	Formacion getFormacionProximoPartido(Long id);
	
	void storeFormacionEstandar(Long id, InfoFormacion infoFormacion);
	
	void storeFormacionProximoPartido(Long id, InfoFormacion infoFormacion);
	
}
