package uy.edu.fing.tsi2.core.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


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
	
	@Inject 
	private ImportacionResource importacionResource;

	@Inject
	TransferenciaResource transferenciaResource;
	
	@Inject
	PushNotificationsResource pushResource;	
	
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
	
	@Path("/importacion")
	public  ImportacionResource getImportacionResource(){
		return importacionResource;
	}
	
	@Path("/transferencia")
	public  TransferenciaResource getTransferenciaResource(){
		return transferenciaResource;
	}
	
	@Path("/registration/id")
	public  PushNotificationsResource getPushNotificationsResource(){
		return pushResource;
	}
}
