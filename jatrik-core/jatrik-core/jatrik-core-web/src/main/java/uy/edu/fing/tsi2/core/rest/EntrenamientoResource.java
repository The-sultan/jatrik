package uy.edu.fing.tsi2.core.rest;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
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
		if (entrenamientoEJB.puedeEntrenar(Long.valueOf(entrenamiento.getIdEquipo()), new Date())){
			for (InfoEntrenamientoJugador jugador : entrenamiento.getJugadores()){
				EnumHabilidad modo = EnumHabilidad.getById(jugador.getModo());
				entrenamientoEJB.entrenarJugador(Long.valueOf(jugador.getIdjugador()), modo);	
			}
			return Response.ok().build();
		}
		else
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
	} 

}
