package uy.edu.fing.tsi2.core.rest;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamiento;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamientoJugador;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEntrenamientoLocal;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumHabilidad;

@RequestScoped
public class EntrenamientoResource {

	@EJB
	private EJBManagerEntrenamientoLocal entrenamientoEJB;
	
	@POST
	public Response entrenarEquipo(InfoEntrenamiento entrenamiento) {
		Date fecha = new Date();
		if (entrenamientoEJB.puedeEntrenar(Long.valueOf(entrenamiento.getIdEquipo()), fecha)){
			for (InfoEntrenamientoJugador jugador : entrenamiento.getJugadores()){
				EnumHabilidad modo = EnumHabilidad.getById(jugador.getModo());
				entrenamientoEJB.entrenarJugador(Long.valueOf(jugador.getIdjugador()), modo);	
			}
			entrenamientoEJB.setFechaEntrenamiento(Long.valueOf(entrenamiento.getIdEquipo()), fecha);
			return Response.ok().build();
		}
		else
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
	} 

	@GET
	@Path("/{id}/puedeEntrenar")
	public Response puedeEntrenarEquipo(@PathParam("id") Long idEquipo) {
	
		if (entrenamientoEJB.puedeEntrenar(idEquipo, new Date())){
			return Response.ok(true).build();
		}
		else
			return Response.ok(false).build();
	} 
	
}
