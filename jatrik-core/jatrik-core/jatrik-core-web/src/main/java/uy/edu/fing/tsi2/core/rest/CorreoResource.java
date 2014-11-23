package uy.edu.fing.tsi2.core.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoCorreo;
import uy.edu.fing.tsi2.jatrik.core.domain.Correo;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerCorreoLocal;

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
			uri = new URI("correo/" + respuesta.getId());

		} catch (URISyntaxException ex) {
			Logger.getLogger(CorreoResource.class.getName()).log(Level.SEVERE,
					null, ex);
			return Response.serverError().build();
		}
		return Response.created(uri).build();
	}
		
	@POST
	@Path("/update")
	public Response actualizarCorreo(InfoCorreo correo) {
		
		Correo respuesta = correoEJB.leerCorreo(correo.getId());
		URI uri = null;
		try {
			uri = new URI("correo/" + respuesta.getId());

		} catch (URISyntaxException ex) {
			Logger.getLogger(CorreoResource.class.getName()).log(Level.SEVERE,
					null, ex);
			return Response.serverError().build();
		}
		return Response.created(uri).build();
	}
	
	@Path("/enviados/{id}")
	@Produces("application/json")
	@GET
	public Response obtenerCorreosEnviadosUsuario(@PathParam("id") Long id){
		List<InfoCorreo> resultado = new ArrayList<InfoCorreo>();
		try {
			List<Correo> emails = correoEJB.obtenerCorreosEnviados(id);
			for (Correo correo : emails) {
					InfoCorreo infoCorreo = new InfoCorreo();
					infoCorreo.setId(correo.getId());
					infoCorreo.setAsunto(correo.getAsunto());
					infoCorreo.setFecha(correo.getFecha());
					infoCorreo.setLeido(correo.getLeido());
					infoCorreo.setMensaje(correo.getMensaje());
					infoCorreo.setTo(correo.getTo().getId());
					infoCorreo.setFrom(correo.getFrom().getId());
                                        infoCorreo.setNickFrom(correo.getFrom().getNick());
                                        infoCorreo.setNickTo(correo.getTo().getNick());
					
					resultado.add(infoCorreo);
			}
			return Response.ok(resultado).build();
		} catch (Exception ex) {
			Logger.getLogger(CorreoResource.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Response.serverError().build();
	}
        
        @Path("/{id}")
	@Produces("application/json")
	@GET
	public Response obtenerCorreosUsuario(@PathParam("id") Long id){
		List<InfoCorreo> resultado = new ArrayList<InfoCorreo>();
		try {
			List<Correo> emails = correoEJB.obtenerCorreos(id);
			for (Correo correo : emails) {
					InfoCorreo infoCorreo = new InfoCorreo();
					infoCorreo.setId(correo.getId());
					infoCorreo.setAsunto(correo.getAsunto());
					infoCorreo.setFecha(correo.getFecha());
					infoCorreo.setLeido(correo.getLeido());
					infoCorreo.setMensaje(correo.getMensaje());
					infoCorreo.setTo(correo.getTo().getId());
					infoCorreo.setFrom(correo.getFrom().getId());
                                        infoCorreo.setNickFrom(correo.getFrom().getNick());
                                        infoCorreo.setNickTo(correo.getTo().getNick());
					
					resultado.add(infoCorreo);
			}
			return Response.ok(resultado).build();
		} catch (Exception ex) {
			Logger.getLogger(CorreoResource.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Response.serverError().build();
	}
}
