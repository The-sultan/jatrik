package uy.edu.fing.tsi2.core.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


import uy.edu.fing.tsi2.jatrik.core.domain.Correo;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerCorreoLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoCorreo;

@RequestScoped
public class CorreoResource {

	@EJB
	private EJBManagerCorreoLocal correoEJB;

	@POST
	@Path("/send")
	public Response enviarCorreo(InfoCorreo correo) {
		
		Correo respuesta = correoEJB.addCorreo(correo.getTo(), correo.getFrom(),correo.getAsunto(),correo.getMensaje());
		URI uri = null;
		try {
			uri = new URI("correo/" + respuesta);

		} catch (URISyntaxException ex) {
			Logger.getLogger(CorreoResource.class.getName()).log(Level.SEVERE,
					null, ex);
			return Response.serverError().build();
		}
		return Response.created(uri).build();
	}
		
}
