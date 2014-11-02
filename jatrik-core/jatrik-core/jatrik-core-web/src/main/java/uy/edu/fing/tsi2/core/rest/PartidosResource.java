package uy.edu.fing.tsi2.core.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEvento;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoPartido;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.domain.eventos.EventoPartido;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerPartidoLocal;

/**
 *
 * @author Farid
 */

@RequestScoped
public class PartidosResource {
	
	@EJB
	EJBManagerPartidoLocal partidoEJB;
	
	@Path("/{id}")
	@Produces("application/json")
	@GET
	public InfoPartido obtenerPartido(@PathParam("id") Long id){
		Partido partido = partidoEJB.obtenerPartido(id);
		InfoPartido infoPartido = new InfoPartido(partido.getId(), partido.getEstado().name(), partido.getLocal().getNombre() , partido.getVisitante().getNombre(), partido.getGolesLocal(), partido.getGolesVisitante());
		List<EventoPartido> eventos = partidoEJB.obtenerEventosPartido(id);
		List<InfoEvento> infoEventos = new ArrayList<>();
		for(EventoPartido eventoPartido : eventos){
			InfoEvento infoEvento = new InfoEvento(eventoPartido.getMinuto(), eventoPartido.toString(), eventoPartido.getEvento().getNombre());
			infoEventos.add(infoEvento);
		}
		infoPartido.setEventos(infoEventos);
		return infoPartido;
	}
	
	@GET
	public Response getPartidosPendientes(){
		List<Partido> partidosPendientes = partidoEJB.obtenerPartidosPendientes();
		List<InfoPartido> infoPartidos = new ArrayList<>();
		for(Partido partido : partidosPendientes){
			infoPartidos.add(new InfoPartido(partido.getId(), partido.getEstado().name(), 
				partido.getLocal().getNombre() , partido.getVisitante().getNombre(), 
				partido.getGolesLocal(), partido.getGolesVisitante()));
		}
		return Response.ok(infoPartidos).build();
	}
}
