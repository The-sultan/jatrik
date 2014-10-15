package uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;
import uy.edu.fing.tsi2.jatrik.core.domain.Comentario;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Formacion;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.domain.eventos.EventoPartidoGol;
import uy.edu.fing.tsi2.jatrik.core.domain.eventos.EventoPartidoJugadaDeGol;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEventos;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoFormacion;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMComentariosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEventosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEventosPartidosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMPartidosLocal;

@Stateless
public class EstrategiaPosibleJugadaGol extends EstrategiaSimulacion{

	private Logger log = Logger.getLogger(EstrategiaPosibleJugadaGol.class);

	
	@EJB
	EJBEMEventosLocal eventosEJB;
	
	@EJB
	EJBEMEventosPartidosLocal eventosPartidosEJB;
	
	@EJB
	EJBEMComentariosLocal comentariosEJB;
	
	@EJB
	EJBEMPartidosLocal partidosEJB;
	
	public EstrategiaPosibleJugadaGol() {
		this.setPeso(40);
	}
	
	
	@Override
	public void manejarEvento(Partido partido) {
		//por ahora, como no tenemos clara la probabilidad de jugada de gol,
		//siempre que se utiliza esta estrategia generamos una jugada de gol.
		Evento eventoJugadaGol = eventosEJB.findByName(EnumEventos.JUGADA_GOL.name());
		boolean equipoLocal = this.getNextBoolean();
		Equipo equipo = equipoLocal ? partido.getLocal() : partido.getVisitante();
		Formacion formacion = equipoLocal ? partido.getFormacionLocal() : partido.getFormacionVisitante();
		Jugador delantero = getJugadorEnPosicion(partido, formacion.getJugadores(), EnumPuestoFormacion.DELANTERO);
		int nivel = this.getNextInt(3) + 1;
		
		List<Comentario> comentarios = comentariosEJB.findComentariosByEventoAndNivel(eventoJugadaGol, Long.valueOf(nivel));
		int comentarioSorteo = this.getNextInt(comentarios.size());
		EventoPartidoJugadaDeGol eventoPartidoJugadaGol = new EventoPartidoJugadaDeGol(partido.getMinuto(), partido, eventoJugadaGol, Long.valueOf(nivel), delantero, equipo);
		eventoPartidoJugadaGol.setComentario(comentarios.get(comentarioSorteo));
		eventosPartidosEJB.add(eventoPartidoJugadaGol);
		
		Equipo elOtroEquipo = equipoLocal ? partido.getVisitante() : partido.getLocal();
		Formacion laOtraFormacion = equipoLocal ? partido.getFormacionVisitante() : partido.getFormacionLocal();
		Jugador golero = getJugadorEnPosicion(partido,laOtraFormacion.getJugadores(), EnumPuestoFormacion.ARQUERO);
		
		int sorteoEsGol = this.getNextInt(10);
		if(sorteoEsGol > 7){
			if(equipoLocal){
				partido.setGolesLocal(partido.getGolesLocal()+1);
			}
			else{
				partido.setGolesVisitante(partido.getGolesVisitante()+1);
			}
			Evento eventoGol = eventosEJB.findByName(EnumEventos.GOL.name());
			EventoPartidoGol eventoPartidoGol = new EventoPartidoGol(partido.getMinuto(), partido, eventoGol, delantero, equipo,
				partido.getGolesLocal(), partido.getGolesVisitante());
			eventosPartidosEJB.add(eventoPartidoGol);

			partidosEJB.update(partido);
		}
		
		
	}

}
