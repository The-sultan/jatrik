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
	
	@Inject
	LigasResource ligasResource;
	
	@Inject
	EquiposResource equiposResource;
	
	@Inject
	SimuladorResource simuladorResource;
	
	@Inject
	LoginResource loginResource;

	@Inject
	EntrenamientoResource entrenamientoResource;	

	
	@Path("/usuarios")
	public UsuariosResource getUsuariosResource(){
		return usuariosResource;
	}
	@Path("/equipos")
	public EquiposResource getEquiposResource(){
		return equiposResource;
	}
	
	@Path("/ligas")
	public  LigasResource getLigasResource(){
		return ligasResource;
	}
	
	@Path("/login")
	public  LoginResource getLoginResource(){
		return loginResource;
	}

	@Path("/entrenamiento")
	public  EntrenamientoResource getEntrenamientoResource(){
		return entrenamientoResource;
	}	

	
	@Path("/simulacion")
	public  SimuladorResource getSimuladorResource(){
		return simuladorResource;
	}
	
	@Path("/partidos/{idEquipo}")
	public PartidosResource getPartidosResource(@PathParam("idEquipo") Long idEquipo){
		partidosResource.setIdEquipo(idEquipo);
		return partidosResource;
	}	
}
