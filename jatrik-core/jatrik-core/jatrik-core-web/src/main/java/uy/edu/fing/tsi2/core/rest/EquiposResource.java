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
	HistorialPartidosResource historialPartidosResource;
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public InfoEquipo getEquipo(@PathParam("id") Long idEquipo){
		Equipo equipo = equipoEJB.find(idEquipo);
		InfoEquipo infoEquipo = new InfoEquipo();
		infoEquipo.setDefensas(getDtoFromEntity(equipo.getFormacion().getJugadores(),EnumPuestoFormacion.DEFENSA));
		infoEquipo.setMediocampistas(getDtoFromEntity(equipo.getFormacion().getJugadores(),EnumPuestoFormacion.MEDIOCAMPISTA));
		infoEquipo.setDelanteros(getDtoFromEntity(equipo.getFormacion().getJugadores(),EnumPuestoFormacion.DELANTERO));
		infoEquipo.setSuplentes(getDtoFromEntity(equipo.getFormacion().getJugadores(),EnumPuestoFormacion.SUPLENTE));
		infoEquipo.setReservas(getDtoFromEntity(equipo.getFormacion().getJugadores(),EnumPuestoFormacion.RESERVA));
		infoEquipo.setGolero(getDtoFromEntity(equipo.getFormacion().getJugadores(),EnumPuestoFormacion.ARQUERO).get(0));
		infoEquipo.setEstadio(getDtoFromEntity(equipo));
		infoEquipo.setFondos(equipo.getFondos());
		infoEquipo.setId(equipo.getId());
		infoEquipo.setNombre(equipo.getNombre());
		
		return infoEquipo;
	}
	
	
	private List<InfoJugador> getDtoFromEntity(Set<JugadorEnFormacion> jugadores, EnumPuestoFormacion puestoEnFormacion){
		List<InfoJugador> infoJugadores = new ArrayList<>();
		for(JugadorEnFormacion jugadorEnFormacion : jugadores){
			if(jugadorEnFormacion.getPuestoFormacion() == puestoEnFormacion){
				InfoJugador infoJugador = getDtoFromEntity(jugadorEnFormacion.getJugador());
				infoJugadores.add(infoJugador);
			}
		}
		return infoJugadores;
	}

	private InfoJugador getDtoFromEntity(Jugador jugador){
		InfoJugador infoJugador = new InfoJugador();
			try {
				BeanUtils.copyProperties(infoJugador, jugador);
				List<InfoHabilidad> infoHabilidades = new ArrayList<>();
				for(Habilidad habilidad : jugador.getHabilidades()){
					InfoHabilidad infoHabilidad = new InfoHabilidad();
					BeanUtils.copyProperties(infoHabilidad, habilidad);
					infoHabilidades.add(infoHabilidad);
					infoHabilidad.setNombre(habilidad.getTipo().getHabilidad());
				}
				infoJugador.setHabilidades(infoHabilidades);
			} catch (IllegalAccessException ex) {
				Logger.getLogger(EquiposResource.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvocationTargetException ex) {
				Logger.getLogger(EquiposResource.class.getName()).log(Level.SEVERE, null, ex);
			}
		return infoJugador;
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
