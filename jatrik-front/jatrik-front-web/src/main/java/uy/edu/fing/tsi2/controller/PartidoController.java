package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import uy.edu.fing.tsi2.front.ejb.interfaces.PartidoEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoPartido;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;



@SuppressWarnings("serial")
@Model
@Named
@SessionScoped
public class PartidoController implements Serializable {

	@Inject
	SessionBeanJatrik sessionBean;
	
	@Named
	@Produces
	InfoPartido partidoDatos;
	
	private int idPartido = 0;
	
	@EJB
	PartidoEJBLocal partidoEJB;
	
	@PostConstruct
	public void initDatos() {
		//Se inicializan los datos cuando desde afuera le setean el id de partido
	}
	
	public void actualizar() {
		this.partidoDatos = partidoEJB.getInfoPartido(idPartido);
	}
	
	
	public void simularPartido(){
		//partidoEJB.simularPartido(1);
	}
	
	public boolean getStopPolling(){
		return partidoDatos.getEstado().equals("FINALIZADO") || partidoDatos.getEstado().equals("PENDIENTE");
	}
	
	public List<InfoPartido> getPartidos(){
		return partidoEJB.getPartidos();
	}
	
	public void simularPartidoAdmin(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String partidoId =  params.get("partidoId"); 
		partidoEJB.simularPartido(Integer.valueOf(partidoId));
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
		this.partidoDatos = partidoEJB.getInfoPartido(idPartido);
	}
}
