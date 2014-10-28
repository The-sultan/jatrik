package uy.edu.fing.tsi2.core.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerSimuladorBeanLocal;


@RequestScoped
public class SimuladorResource {

	@EJB
	EJBManagerSimuladorBeanLocal simuladorBean;
	
	@GET
	public String simularPartido(@QueryParam("partido") Long idPartido){
		simuladorBean.simularPartido(idPartido);
		return "Todo bien";
	}
}
