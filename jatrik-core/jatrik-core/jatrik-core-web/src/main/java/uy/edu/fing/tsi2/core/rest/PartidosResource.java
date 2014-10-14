package uy.edu.fing.tsi2.core.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	@GET
	public InfoPartido obtenerPartido(@PathParam("id") Long id){
		Partido partido = partidoEJB.obtenerPartido(id);
		InfoPartido infoPartido = new InfoPartido(partido.getId(), partido.getEstado().name(),partido.getLocal().getNombre() , partido.getVisitante().getNombre(), partido.getGolesLocal(), partido.getGolesVisitante());
		List<EventoPartido> eventos = partidoEJB.obtenerEventosPartido(id);
		List<InfoEvento> infoEventos = new ArrayList<>();
		for(EventoPartido eventoPartido : eventos){
			InfoEvento infoEvento = new InfoEvento(eventoPartido.getMinuto(), eventoPartido.toString());
			infoEventos.add(infoEvento);
		}
		infoPartido.setEventos(infoEventos);
		return infoPartido;
	}
}
