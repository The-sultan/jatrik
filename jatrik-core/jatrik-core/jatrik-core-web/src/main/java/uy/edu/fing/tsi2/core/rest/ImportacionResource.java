package uy.edu.fing.tsi2.core.rest;


import java.io.Console;

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
		for (int i = 0; i < 20000; i++) {
			try {
				System.out.println(i); 
				importacion.ImportarJugadores(i);
				
			} catch (Exception e) {
				
			}
			
		}
		
		return Response.ok().build();
	}
}
