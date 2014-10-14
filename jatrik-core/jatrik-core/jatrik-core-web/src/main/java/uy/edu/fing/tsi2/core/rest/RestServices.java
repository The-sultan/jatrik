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
	private UsuariosResource usuariosResource;
	
	@Inject
	private LigasResource ligasResource;
	
	@Inject
	private EquiposResource equiposResource;
	
	@Inject
	private SimuladorResource simuladorResource;
	
	@Inject
	private LoginResource loginResource;

	@Inject
	private EntrenamientoResource entrenamientoResource;	
	
	@Inject
	private PartidosResource partidosResource;

	
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
	
	@Path("/partidos")
	public  PartidosResource getPartidosResource(){
		return partidosResource;
	}
	
}
