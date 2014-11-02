package uy.edu.fing.tsi2.core.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.beanutils.BeanUtils;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEstadio;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoHabilidad;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Habilidad;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.JugadorEnFormacion;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEquipoBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoFormacion;

/**
  * @author Farid
 */

@RequestScoped
public class EquiposResource {

	@EJB
	private EJBManagerEquipoBeanLocal equipoEJB;
	
	@Inject
	private HistorialPartidosResource historialPartidosResource;
	
	@Inject
	private FormacionesResource formacionesResource;
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public InfoEquipo getEquipo(@PathParam("id") Long idEquipo){
		Equipo equipo = equipoEJB.find(idEquipo);
		InfoEquipo infoEquipo = new InfoEquipo();

		infoEquipo.setEstadio(getDtoFromEntity(equipo));
		infoEquipo.setFondos(equipo.getFondos());
		infoEquipo.setId(equipo.getId());
		infoEquipo.setNombre(equipo.getNombre());
		
		return infoEquipo;
	}
	
	@Path("/{id}/formaciones")
	public FormacionesResource getFormaciones(@PathParam("id") Long idEquipo){
		formacionesResource.setId(idEquipo);
		return formacionesResource;
	}
	
	
	private InfoEstadio getDtoFromEntity(Equipo equipo){
		InfoEstadio infoEstadio = new InfoEstadio();
		infoEstadio.setAltura(equipo.getAltura());
		infoEstadio.setLatitud(equipo.getLatitud());
		infoEstadio.setLongitud(equipo.getLongitud());
		infoEstadio.setNombre(equipo.getEstadio());
		return infoEstadio;
	}
	
	@Path("/{id}/historial")
	public HistorialPartidosResource getHistorialPartidosResource(@PathParam("id") Long idEquipo){
			historialPartidosResource.setIdEquipo(idEquipo);
		return historialPartidosResource;
	}
}
