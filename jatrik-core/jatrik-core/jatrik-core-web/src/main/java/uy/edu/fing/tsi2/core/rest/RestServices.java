package uy.edu.fing.tsi2.core.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


/**
 * @author Farid
 */
@RequestScoped
@Path("")
public class RestServices {
	
	@Inject
	UsuariosResource usuariosResource;
	@Inject
	PartidosResource partidosResource;
	
	@Path("/usuarios")
	public UsuariosResource getUsuariosResource(){
		return usuariosResource;
	}
	
	@Path("/partidos")
	public PartidosResource getPartidosResource(){
		return partidosResource;
	}	
}
