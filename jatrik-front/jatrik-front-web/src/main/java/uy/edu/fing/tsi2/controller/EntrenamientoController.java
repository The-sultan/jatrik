package uy.edu.fing.tsi2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamientoJugador;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoFormacion;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;
import uy.edu.fing.tsi2.model.Equipo.Equipo;

@Model
@RequestScoped
public class EntrenamientoController {
 
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
		equipoDatos = new Equipo();
		InfoFormacion formacion = equipoEJB.getFormacionEstandar(sessionBean.getInfoUsuario().getInfoEquipo().getId());
		List<InfoJugador> titulares = new ArrayList<>();
		titulares.add(formacion.getGolero());
		for(InfoJugador jugador : formacion.getDefensas()){
			titulares.add(jugador);
		}
		for(InfoJugador jugador : formacion.getMediocampistas()){
			titulares.add(jugador);
		}
		for(InfoJugador jugador : formacion.getDelanteros()){
			titulares.add(jugador);
		}
	
		equipoDatos.setTitulares(titulares);
	}
	
    public String entrenar() {
		FacesMessage msg = null;
		List<InfoEntrenamientoJugador> entrenamiento = new ArrayList<InfoEntrenamientoJugador>();
		// Aca se obtienen los valores de los radio buttons
    	String Respuesta = equipoEJB.entrenarEquipo(sessionBean.getInfoUsuario().getInfoEquipo().getId(), entrenamiento);
    	
    	if(Respuesta.contains("Ya"))
    		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Respuesta);
    	else
    		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", Respuesta);
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    	return null;
    }	

	public EntrenamientoController() {
		
	}
	
	

}
