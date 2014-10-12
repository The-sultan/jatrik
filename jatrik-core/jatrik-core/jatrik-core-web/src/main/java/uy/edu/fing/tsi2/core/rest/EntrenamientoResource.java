package uy.edu.fing.tsi2.core.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamiento;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEntrenamientoLocal;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumHabilidad;

@RequestScoped
public class EntrenamientoResource {

	@EJB
	private EJBManagerEntrenamientoLocal entrenamientoEJB;
			
	@POST
	public String entrenarEquipo(InfoEntrenamiento entrenamiento) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaEntrenamiento = new Date();
		try {
			fechaEntrenamiento = (Date) dateFormat.parse(entrenamiento.getFecha());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		EnumHabilidad modo = EnumHabilidad.getById(entrenamiento.getModo());
		String response = entrenamientoEJB.entrenarEquipo(Long.valueOf(entrenamiento.getIdequipo()), fechaEntrenamiento, modo);

		return response;
	}

}
