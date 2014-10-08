package uy.edu.fing.tsi2.core.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.apache.commons.beanutils.BeanUtils;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEstadio;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.JugadorEnFormacion;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEquipoBeanLocal;

/**
  * @author Farid
 */

@RequestScoped
public class EquiposResource {

	@EJB
	private EJBManagerEquipoBeanLocal equipoEJB;
	
	@GET
	@Path("/{id}")
	public InfoEquipo getEquipo(@PathParam("id") Long idEquipo){
		Equipo equipo = equipoEJB.find(idEquipo);
		InfoEquipo infoEquipo = new InfoEquipo();
		/*
		infoEquipo.setDefensas(getDtoFromEntity(equipo.getFormacion().getDefensas()));
		infoEquipo.setMediocampistas(getDtoFromEntity(equipo.getFormacion().getMediocampistas()));
		infoEquipo.setDelanteros(getDtoFromEntity(equipo.getFormacion().getDelanteros()));
		infoEquipo.setReservas(getDtoFromEntitySet(equipo.getFormacion().getJugadoresReserva()));
		infoEquipo.setSuplentes(getDtoFromEntitySet(equipo.getFormacion().getJugadoresSuplentes()));
		infoEquipo.setGolero(getDtoFromEntity(equipo.getFormacion().getArquero()));
		*/
		infoEquipo.setEstadio(getDtoFromEntity(equipo));
		
		infoEquipo.setFondos(equipo.getFondos());
		infoEquipo.setId(equipo.getId());
		infoEquipo.setNombre(equipo.getNombre());
		
		return infoEquipo;
	}
	
	
	private List<InfoJugador> getDtoFromEntity(Set<JugadorEnFormacion> jugadores){
		List<InfoJugador> infoJugadores = new ArrayList<>();
		for(JugadorEnFormacion jugadorEnFormacion : jugadores){
			InfoJugador infoJugador = getDtoFromEntity(jugadorEnFormacion.getJugador());
			infoJugadores.add(infoJugador);
		}
		return infoJugadores;
	}

	private List<InfoJugador> getDtoFromEntitySet(Set<Jugador> jugadores){
		List<InfoJugador> infoJugadores = new ArrayList<>();
		for(Jugador jugador : jugadores){
			InfoJugador infoJugador = getDtoFromEntity(jugador);
			infoJugadores.add(infoJugador);
		}
		return infoJugadores;
	}
	
	private InfoJugador getDtoFromEntity(Jugador jugador){
		InfoJugador infoJugador = new InfoJugador();
			try {
				BeanUtils.copyProperties(infoJugador, jugador);
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
}
