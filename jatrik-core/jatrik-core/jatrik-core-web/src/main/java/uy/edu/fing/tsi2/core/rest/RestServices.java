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
	
	@Inject
	LigasResource ligasResource;
	
	@Path("/usuarios")
	public UsuariosResource getUsuariosResource(){
		return usuariosResource;
	}
	
	@Path("/ligas")
	public  LigasResource getLigasResource(){
		return ligasResource;
	}
}

package uy.edu.fing.tsi2.core.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


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
	
	@Path("/partidos/{idEquipo}")
	@Produces("application/json")
	public PartidosResource getPartidosResource(@PathParam("idEquipo") Long idEquipo){
		partidosResource.setIdEquipo(idEquipo);
		return partidosResource;
	}	
}
