package uy.edu.fing.tsi2.core.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerPartidoLocal;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;


/**
 *
 * @author Rafael
 */

@RequestScoped
public class PartidosResource {
	
	@EJB
	private EJBManagerPartidoLocal partidoEJB;
	
	@GET
	public List<Partido> obtenerPartidos(){
		List<Partido> prt = partidoEJB.obtenerPartidos(Long.valueOf(1));
//		URI uri = null;
//		try {
//			uri = new URI("partidos/" + idEquipo);
//			
//		} catch (URISyntaxException ex) {
//			Logger.getLogger(PartidosResource.class.getName()).log(Level.SEVERE, null, ex);
//			return Response.serverError().build();
//		}
//		return Response.created(uri).build();
		return prt;
	}
	
}
