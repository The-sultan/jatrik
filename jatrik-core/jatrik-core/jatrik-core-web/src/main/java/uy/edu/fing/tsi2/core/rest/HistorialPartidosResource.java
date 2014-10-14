package uy.edu.fing.tsi2.core.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerPartidoLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoPartido;
import uy.edu.fing.tsi2.jatrik.common.payloads.HistorialPartidos;

/**
 *
 * @author Rafael
 */

@RequestScoped
public class HistorialPartidosResource {
	
	@EJB
	private EJBManagerPartidoLocal partidoEJB;
	
	private Long idEquipo;
	
	public void setIdEquipo(Long id){
		idEquipo = id;
	}
	
	@GET
	@Produces("application/json")
	public HistorialPartidos obtenerPartidos(){
		List<Partido> prt = partidoEJB.obtenerPartidos(idEquipo);
		List<InfoPartido> partidos = new ArrayList<InfoPartido>();
		for (Partido p : prt){
			InfoPartido ip = new InfoPartido(p.getId(), p.getEstado().getEstado(), p.getLocal().getNombre(),
											 p.getVisitante().getNombre(), p.getGolesLocal(), p.getGolesVisitante());
			partidos.add(ip);
		}
		HistorialPartidos historial = new HistorialPartidos(partidos);
		return historial;
	}
	
}
