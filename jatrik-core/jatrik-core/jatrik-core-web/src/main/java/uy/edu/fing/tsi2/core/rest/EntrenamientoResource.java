package uy.edu.fing.tsi2.core.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamiento;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEntrenamientoLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerUsuarioLocal;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumHabilidad;

@RequestScoped
public class EntrenamientoResource {

	@EJB
	private EJBManagerEntrenamientoLocal entrenamientoEJB;
	
	@POST
	public Response entrenarEquipo(InfoEntrenamiento entrenamiento) {
		EnumHabilidad modo = EnumHabilidad.getById(entrenamiento.getModo());
		String respuesta = entrenamientoEJB.entrenarEquipo(Long.valueOf(entrenamiento.getIdequipo()), new Date(), modo);	
		if (respuesta.contains("Has entrenado"))
			return Response.ok(respuesta).build();
		else
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
	} 

}
