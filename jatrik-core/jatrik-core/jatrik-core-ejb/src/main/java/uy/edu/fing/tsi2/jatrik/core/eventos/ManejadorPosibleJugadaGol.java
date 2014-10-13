package uy.edu.fing.tsi2.jatrik.core.eventos;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.ejb.IPartidos;

public class ManejadorPosibleJugadaGol extends ManejarEvento{

	private Logger log = Logger.getLogger(ManejadorPosibleJugadaGol.class);
	
	@Override
	public void manejarEvento(IPartidos partidosManager, Partido partido, Evento evento) {
		// TODO Auto-generated method stub
		log.info("### jugada de gol###");
		
		if(partido.getMinuto() % 2 == 0){
			log.info(String.format("### gol de %s###", partido.getVisitante().getNombre()));
		}else{
			log.info(String.format("### gol de %s###", partido.getLocal().getNombre()));
		}
	}

}
