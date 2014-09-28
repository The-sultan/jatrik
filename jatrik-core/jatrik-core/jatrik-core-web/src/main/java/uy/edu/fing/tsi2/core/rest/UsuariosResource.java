package uy.edu.fing.tsi2.core.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import uy.edu.fing.tsi2.jatrik.common.payloads.Usuario;
import uy.edu.fing.tsi2.jatrik.core.ejb.UsuarioEJBLocal;


/**
 *
 * @author Farid
 */
@Stateless
public class UsuariosResource {
	
	@EJB
	private UsuarioEJBLocal usuarioEJB;
	
	@POST
	public Response crearUsuario(Usuario usuario){
		Long usuarioId = usuarioEJB.crearUsuario(usuario.getNombre(), usuario.getEmail(), 
				usuario.getNick(), usuario.getPassword());
		URI uri = null;
		try {
			uri = new URI("usuarios/" + usuarioId);
			
		} catch (URISyntaxException ex) {
			Logger.getLogger(UsuariosResource.class.getName()).log(Level.SEVERE, null, ex);
			return Response.serverError().build();
		}
		return Response.created(uri).build();
	}
	
}
