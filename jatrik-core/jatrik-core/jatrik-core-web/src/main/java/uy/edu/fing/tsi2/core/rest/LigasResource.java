package uy.edu.fing.tsi2.core.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEstadio;

import uy.edu.fing.tsi2.jatrik.common.payloads.Liga;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEquipoBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerLigaBeanLocal;

@RequestScoped
public class LigasResource {

	@EJB
	private EJBManagerLigaBeanLocal ligaEJB;
	
	@EJB
	private EJBManagerEquipoBeanLocal equipoEJB;

	@POST
	public Response crearLiga(Liga liga) {
		Long ligaId = ligaEJB.creandoMiLiga(liga.getNombre());
		URI uri = null;
		try {
			uri = new URI("ligas/" + ligaId);

		} catch (URISyntaxException ex) {
			Logger.getLogger(LigasResource.class.getName()).log(Level.SEVERE,
					null, ex);
			return Response.serverError().build();
		}
		return Response.created(uri).build();
	}

}
