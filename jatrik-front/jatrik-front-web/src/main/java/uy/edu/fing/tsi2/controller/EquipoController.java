package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

                                                                                                                                                                                                          



import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;
import uy.edu.fing.tsi2.model.Equipo.Equipo;



@SuppressWarnings("serial")
@Model
@RequestScoped
public class EquipoController  implements Serializable {
	
	@Inject
	SessionBeanJatrik sessionBean;
	
	@EJB
	EquipoEJBLocal equipoEJB;
	
	@Named
	@Produces
	Equipo equipoDatos;
	
	@PostConstruct
	public void initDatos() {

		//cargar el equipo
		
		//TODO:Obtener el id del loginBean
		InfoEquipo equipoTemp = equipoEJB.getEquipo(sessionBean.getInfo().getInfoEquipo().getId());
		equipoDatos = new Equipo();
		List<InfoJugador> titulares = new ArrayList<>();
		titulares.add(equipoTemp.getGolero());
		titulares.addAll(equipoTemp.getDefensas());
		titulares.addAll(equipoTemp.getMediocampistas());
		titulares.addAll(equipoTemp.getDelanteros());
		equipoDatos.setTitulares(titulares);
		equipoDatos.setSuplentes(equipoTemp.getSuplentes());
		equipoDatos.setReserva(equipoTemp.getReservas());
		
		//TODO:Traducir 
		//equipoDatos = new Equipo();
		
		//equipoTemp.setNombre(equipoTemp.getNombre());
		
		//equipoDatos.setFormacion("4-3-3");
		
		//ArrayList<InfoJugador> titulares = new ArrayList<InfoJugador>() ;
		
		//titulares.add(equipoTemp.getGolero());
		//titulares.addAll(equipoTemp.getDefensas());
		//titulares.addAll(equipoTemp.getMediocampistas());
		//titulares.addAll(equipoTemp.getDelanteros());
		
		//equipoDatos.setTitulares(titulares);
		
		//equipoDatos.setSuplentes(new ArrayList<>(equipoTemp.getSuplentes()));
		//equipoDatos.setReserva(new ArrayList<>(equipoTemp.getReservas()));
		
		
	}
	
}
