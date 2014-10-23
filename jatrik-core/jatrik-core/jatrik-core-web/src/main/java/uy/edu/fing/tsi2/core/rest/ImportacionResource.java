package uy.edu.fing.tsi2.core.rest;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans.EJBManagerImportacion;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerImportacionDatosLocal;

@RequestScoped
public class ImportacionResource {

	@EJB
	private EJBManagerImportacionDatosLocal importacion;
	
	@POST
	public Response ImportarJugadores(){
		
		importacion.ImportarJugadores();
		
		return Response.ok().build();
	}
}
