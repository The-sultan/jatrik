package uy.edu.fing.tsi2.core.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerPartidoLocal;


/**
 *
 * @author Rafael
 */

@RequestScoped
public class PartidosResource {
	
	@EJB
	private EJBManagerPartidoLocal partidoEJB;
	private Long idEquipo;
	
	public void setIdEquipo(Long id){
		idEquipo = id;
	}
	
	@GET
	public List<Partido> obtenerPartidos(){
		List<Partido> prt = partidoEJB.obtenerPartidos(idEquipo);
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
