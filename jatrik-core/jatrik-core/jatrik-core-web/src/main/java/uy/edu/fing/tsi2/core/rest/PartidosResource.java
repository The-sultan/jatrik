package uy.edu.fing.tsi2.core.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		String fecha = new SimpleDateFormat("dd/MM/yyyy").format(partido.getFechaInicio());
		infoPartido.setFecha(fecha);
		List<EventoPartido> eventos = partidoEJB.obtenerEventosPartido(id);
		List<InfoEvento> infoEventos = new ArrayList<>();
		for(EventoPartido eventoPartido : eventos){
			InfoEvento infoEvento = new InfoEvento(eventoPartido.getMinuto(), eventoPartido.toString(), eventoPartido.getEvento().getNombre());
			infoEventos.add(infoEvento);
		}
		Collections.sort(infoEventos, new Comparator<InfoEvento>(){
			@Override
			public int compare(InfoEvento e1, InfoEvento e2){
				if(e1.getMinuto() > e2.getMinuto()){
					return -1;
				}
				else if(e1.getMinuto() == e2.getMinuto()){
					return 0;
				}
				else{
					return 1;
				}
			}
			
		});
		infoPartido.setEventos(infoEventos);
		return infoPartido;
	}
	
	@GET
	public Response getPartidosPendientes(){
		List<Partido> partidosPendientes = partidoEJB.obtenerPartidosPendientes();
		List<InfoPartido> infoPartidos = new ArrayList<>();
		for(Partido partido : partidosPendientes){
			String fecha = new SimpleDateFormat("dd/MM/yyyy").format(partido.getFechaInicio());
			InfoPartido infoPartido = new InfoPartido(partido.getId(), partido.getEstado().name(), 
				partido.getLocal().getNombre() , partido.getVisitante().getNombre(), 
				partido.getGolesLocal(), partido.getGolesVisitante());
			infoPartido.setFecha(fecha);
			infoPartidos.add(infoPartido);
		}
		return Response.ok(infoPartidos).build();
	}
}
