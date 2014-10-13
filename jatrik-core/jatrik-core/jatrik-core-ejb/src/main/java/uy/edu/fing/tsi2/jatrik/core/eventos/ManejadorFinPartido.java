package uy.edu.fing.tsi2.jatrik.core.eventos;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.ejb.IPartidos;



@Stateless
public class ManejadorFinPartido extends ManejarEvento {
	private Logger log = Logger.getLogger(ManejadorFinPartido.class);

	@Override
	public void manejarEvento(IPartidos partidosManager, Partido partido, Evento evento) {
		log.info("##### Fin del Partido #####");
		

	}

}
