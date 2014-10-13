package uy.edu.fing.tsi2.jatrik.core.eventos;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.ejb.IPartidos;

public class ManejadorLesion extends ManejarEvento{
	
	private Logger log = Logger.getLogger(ManejadorLesion.class);
	
	@Override
	public void manejarEvento(IPartidos partidosManager, Partido partido,
			Evento evento) {
		
		log.info("##### Se lesiono uno #####");
	}

}
