package uy.edu.fing.tsi2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import uy.edu.fing.tsi2.front.ejb.interfaces.PartidoEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoPartido;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;



@SuppressWarnings("serial")
@Model
@RequestScoped
public class PartidoController implements Serializable {

	@Inject
	SessionBeanJatrik sessionBean;
	
	@Named
	@Produces
	InfoPartido partidoDatos;
	
	@EJB
	PartidoEJBLocal partidoEJB;
	
	@PostConstruct
	public void initDatos() {
		//Obtener la info del partido
		
		this.partidoDatos = partidoEJB.getInfoPartido(1);
	}
	
	
}
