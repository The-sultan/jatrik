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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.apache.commons.beanutils.BeanUtils;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoFormacion;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoHabilidad;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Formacion;
import uy.edu.fing.tsi2.jatrik.core.domain.Habilidad;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.JugadorEnFormacion;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEquipoBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoFormacion;

/**
 * @author Farid
 */

@RequestScoped
public class FormacionesResource {
	
	@EJB
	EJBManagerEquipoBeanLocal equipoEJB;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@GET
	@Path("/estandar")
	public Response getFormacionEstandar(){
		Formacion formacion = equipoEJB.getFormacionEstandar(id);
		return Response.ok(getDtoFromEntity(formacion)).build();
	}
	
	@POST
	@Path("/estandar")
	public Response saveFormacionEstandar(InfoFormacion infoFormacion){
		equipoEJB.storeFormacionEstandar(id, infoFormacion);
		return Response.ok().build();
	}
	
	@GET
	@Path("/proximoPartido")
	public Response getFormacionProximoPartido(){
		Formacion formacion = equipoEJB.getFormacionProximoPartido(id);
		return Response.ok(getDtoFromEntity(formacion)).build();
	}
	
	@POST
	@Path("/proximoPartido")
	public Response saveFormacionProximoPartido(InfoFormacion infoFormacion){
		equipoEJB.storeFormacionProximoPartido(id, infoFormacion);
		return Response.ok().build();
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
	
	private InfoFormacion getDtoFromEntity(Formacion formacion){
		InfoFormacion infoFormacion = new InfoFormacion();
		if(formacion != null){
			infoFormacion.setGolero(getDtoFromEntity(formacion.getJugadores(),EnumPuestoFormacion.ARQUERO).get(0));
			infoFormacion.setDefensas(getDtoFromEntity(formacion.getJugadores(), EnumPuestoFormacion.DEFENSA));
			infoFormacion.setMediocampistas(getDtoFromEntity(formacion.getJugadores(), EnumPuestoFormacion.MEDIOCAMPISTA));
			infoFormacion.setDelanteros(getDtoFromEntity(formacion.getJugadores(), EnumPuestoFormacion.DELANTERO));
			infoFormacion.setSuplentes(getDtoFromEntity(formacion.getJugadores(), EnumPuestoFormacion.SUPLENTE));
			infoFormacion.setReservas(getDtoFromEntity(formacion.getJugadores(), EnumPuestoFormacion.RESERVA));
		}
		return infoFormacion;
	}
}
