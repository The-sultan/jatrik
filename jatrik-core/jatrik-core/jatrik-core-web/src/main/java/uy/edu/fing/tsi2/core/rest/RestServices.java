package uy.edu.fing.tsi2.core.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;


/**
 * @author Farid
 */
@RequestScoped
@Path("")
public class RestServices {
	
	@Inject
	UsuariosResource usuariosResource;
	
	@Path("/usuarios")
	public UsuariosResource getUsuariosResource(){
		return usuariosResource;
	}
	
	
}
