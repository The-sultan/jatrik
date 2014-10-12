package uy.edu.fing.tsi2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;




import uy.edu.fing.tsi2.front.ejb.implementations.EquipoEJB;                                                                                                                                                                                                            
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.model.Equipo.Equipo;



@SuppressWarnings("serial")
@Model
@RequestScoped
public class EquipoController  implements Serializable {
	
	@Inject 
	LoginController loginBean;
	
	@EJB
	EquipoEJB equipoEJB;
	

	Equipo equipo;
	
	@PostConstruct
	public void initDatos() {

		//cargar el equipo
		
		//TODO:Obtener el id del loginBean
		InfoEquipo equipoTemp = equipoEJB.getEquipo(0);

		
		//TODO:Traducir 
		
		
	}
	
}
