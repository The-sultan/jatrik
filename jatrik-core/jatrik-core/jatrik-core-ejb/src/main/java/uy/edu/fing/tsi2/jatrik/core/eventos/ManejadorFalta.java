package uy.edu.fing.tsi2.jatrik.core.eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.JugadorEnFormacion;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.ejb.IPartidos;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoFormacion;

public class ManejadorFalta extends ManejarEvento{
	
	private Logger log = Logger.getLogger(ManejadorFinPartido.class);

	@Override
	public void manejarEvento(IPartidos partidosManager, Partido partido, Evento evento) {

		
		//Jugador delantero = getDelantero(partido.getFormacionLocal().getJugadores());
		log.info(String.format("falta sobre el jugador"));
	}
	
	
	private Jugador getDelantero(Set<JugadorEnFormacion> jugadores){
		List<Jugador> jugadoresSeleccionados = new ArrayList<>();
		for(JugadorEnFormacion jugador : jugadores){
			if(jugador.getPuestoFormacion().equals(EnumPuestoFormacion.DELANTERO)){
				jugadoresSeleccionados.add(jugador.getJugador());
			}
		}
		Random random = new Random(jugadores.size()-1);
		return jugadoresSeleccionados.get(random.nextInt());
	}

	
}
