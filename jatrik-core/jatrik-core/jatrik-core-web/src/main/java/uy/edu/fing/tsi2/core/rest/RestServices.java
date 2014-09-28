package uy.edu.fing.tsi2.core.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;


/**
 * @author Farid
 */

@Path("")
public class RestServices {
	
	@Inject
	UsuariosResource usuariosResource;
	
	@Path("/usuarios")
	public UsuariosResource getUsuariosResource(){
		return usuariosResource;
	}
	
	
}
