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
import uy.edu.fing.tsi2.jatrik.core.domain.eventos.EventoPartidoFalta;
import uy.edu.fing.tsi2.jatrik.core.domain.eventos.EventoPartidoTarjetaAmarilla;
import uy.edu.fing.tsi2.jatrik.core.domain.eventos.EventoPartidoTarjetaAmarillaDoble;
import uy.edu.fing.tsi2.jatrik.core.domain.eventos.EventoPartidoTarjetaRoja;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEventos;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoFormacion;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMComentariosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEventosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEventosPartidosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMPartidosLocal;

@Stateless
public class EstrategiaFalta extends EstrategiaSimulacion{
	
	@EJB
	EJBEMEventosLocal eventosEJB;
	
	@EJB
	EJBEMEventosPartidosLocal eventosPartidosEJB;
	
	@EJB
	EJBEMComentariosLocal comentariosEJB;
	
	@EJB
	EJBEMPartidosLocal partidosEJB;
	
	public EstrategiaFalta(){
		this.setPeso(20);
	}
	
	private Logger log = Logger.getLogger(EstrategiaIdle.class);

	@Override
	public void manejarEvento(Partido partido) {

		Evento eventoFalta = eventosEJB.findByName(EnumEventos.FALTA.name());
		boolean equipoLocal = this.getNextBoolean();
		Equipo equipo = equipoLocal ? partido.getLocal() : partido.getVisitante();
		Formacion formacion = equipoLocal ? partido.getFormacionLocal() : partido.getFormacionVisitante();
		Jugador delantero = getJugadorEnPosicion(partido, formacion.getJugadores(), EnumPuestoFormacion.DELANTERO);
		int nivel = this.getNextInt(3) + 1;
		
		List<Comentario> comentarios = comentariosEJB.findComentariosByEventoAndNivel(eventoFalta, Long.valueOf(nivel));
		int comentarioSorteo = this.getNextInt(comentarios.size());
		EventoPartidoFalta eventoPartidoFalta = new EventoPartidoFalta(partido.getMinuto(), partido, eventoFalta, Long.valueOf(nivel), delantero, equipo);
		eventoPartidoFalta.setComentario(comentarios.get(comentarioSorteo));
		eventosPartidosEJB.add(eventoPartidoFalta);
		
		Equipo elOtroEquipo = equipoLocal ? partido.getVisitante() : partido.getLocal();
		Formacion laOtraFormacion = equipoLocal ? partido.getFormacionVisitante() : partido.getFormacionLocal();
		Jugador defensaOMediocampista = getJugadorEnPosicion(partido,laOtraFormacion.getJugadores(), EnumPuestoFormacion.DEFENSA, EnumPuestoFormacion.MEDIOCAMPISTA);
		boolean expulsado = false;
		if(defensaOMediocampista != null){
			if(nivel == 2){

				if(!partido.getJugadoresConTarjetaAmarilla().contains(defensaOMediocampista)){
					Evento eventoTarjeta = eventosEJB.findByName(EnumEventos.TARJETA_AMARILLA.name());
					EventoPartidoTarjetaAmarilla eventoPartidoTarjeta = new EventoPartidoTarjetaAmarilla(partido.getMinuto(), partido, eventoTarjeta, defensaOMediocampista, elOtroEquipo);
					eventosPartidosEJB.add(eventoPartidoTarjeta);
					partido.getJugadoresConTarjetaAmarilla().add(defensaOMediocampista);
				}else{//doble tarjeta amarilla, expulsado
					Evento eventoTarjeta = eventosEJB.findByName(EnumEventos.TARJETA_AMARILLA_DOBLE.name());
					EventoPartidoTarjetaAmarillaDoble eventoPartidoTarjeta = new EventoPartidoTarjetaAmarillaDoble(partido.getMinuto(), partido, eventoTarjeta, defensaOMediocampista, elOtroEquipo);
					eventosPartidosEJB.add(eventoPartidoTarjeta);
					expulsado = true;
					partido.getJugadoresExpulsados().add(defensaOMediocampista);
				}

			}
			if(nivel == 3 || expulsado){
					Evento eventoTarjeta = eventosEJB.findByName(EnumEventos.TARJETA_ROJA.name());
					EventoPartidoTarjetaRoja eventoPartidoTarjeta = new EventoPartidoTarjetaRoja(partido.getMinuto(), partido, eventoTarjeta, defensaOMediocampista, elOtroEquipo);
					eventosPartidosEJB.add(eventoPartidoTarjeta);
					partido.getJugadoresExpulsados().add(defensaOMediocampista);
			}

			if(nivel > 1){
				partidosEJB.update(partido);
			}
		}
		
		
	}
	
	
	

}
