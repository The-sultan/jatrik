package uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import uy.edu.fing.tsi2.jatrik.core.domain.Comentario;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Formacion;

import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.JugadorEnFormacion;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.domain.eventos.EventoPartidoLesion;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEventos;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoFormacion;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMComentariosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEventosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEventosPartidosLocal;

@Stateless
public class EstrategiaFalta extends EstrategiaSimulacion{
	
	@EJB
	EJBEMEventosLocal eventosEJB;
	
	@EJB
	EJBEMEventosPartidosLocal eventosPartidosEJB;
	
	@EJB
	EJBEMComentariosLocal comentariosEJB;
	
	public EstrategiaFalta(){
		this.setPeso(100);
	}
	
	private Logger log = Logger.getLogger(EstrategiaFinPartido.class);

	@Override
	public void manejarEvento(Partido partido) {

		Evento eventoFalta = eventosEJB.findByName(EnumEventos.FALTA.name());
		boolean equipoLocal = this.getNextBoolean();
		Equipo equipo = equipoLocal ? partido.getLocal() : partido.getVisitante();
		Formacion formacion = equipoLocal ? partido.getFormacionLocal() : partido.getFormacionVisitante();
		Jugador delantero = getJugadorEnPosicion(formacion.getJugadores(), EnumPuestoFormacion.DELANTERO);
		int nivel = this.getNextInt(3) + 1;
		if(nivel == 2){
			//fue para tarjeta
			Equipo elOtroEquipo = equipoLocal ? partido.getVisitante() : partido.getLocal();
			Formacion laOtraFormacion = equipoLocal ? partido.getFormacionVisitante() : partido.getFormacionLocal();
			Jugador defensaODelantero = getJugadorEnPosicion(laOtraFormacion.getJugadores(), EnumPuestoFormacion.DEFENSA, EnumPuestoFormacion.MEDIOCAMPISTA);
			
		}
		List<Comentario> comentarios = comentariosEJB.findComentariosByEventoAndNivel(eventoFalta, Long.valueOf(nivel));
		int comentarioSorteo = this.getNextInt(comentarios.size());
		EventoPartidoLesion eventoLesion = new EventoPartidoLesion(partido.getMinuto(), partido, eventoFalta, Long.valueOf(nivel), delantero, equipo);
		eventoLesion.setComentario(comentarios.get(comentarioSorteo));
		eventosPartidosEJB.add(eventoLesion);
		
	}
	
	
	private Jugador getJugadorEnPosicion(Set<JugadorEnFormacion> jugadores, EnumPuestoFormacion... puestos){
		List<Jugador> jugadoresSeleccionados = new ArrayList<>();
		for(JugadorEnFormacion jugador : jugadores){
			boolean agregar = false;
			for(EnumPuestoFormacion puesto : puestos){
				if(jugador.getPuestoFormacion().equals(puesto)){
					agregar = true;
					break;
				}
			}
			if(agregar){
				jugadoresSeleccionados.add(jugador.getJugador());
			}
		}
		Random random = new Random(jugadores.size()-1);
		return jugadoresSeleccionados.get(random.nextInt(jugadoresSeleccionados.size()));
	}

}
