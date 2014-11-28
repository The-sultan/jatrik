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
	private TransferenciaResource transferenciaResource;
	
	@Inject
	private PushNotificationsResource pushResource;	
	
	@Inject
	private CorreoResource correoResource;	
	
	@Inject
	private RssResource rssResource;
	
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
	
	@Path("/correo")
	public  CorreoResource getCorreoResource(){
		return correoResource;
	}
	
	@Path("/registration/id")
	public  PushNotificationsResource getPushNotificationsResource(){
		return pushResource;
	}
	
	@Path("/rss")
	public RssResource getRssResource(){
		return rssResource;
	}
}
