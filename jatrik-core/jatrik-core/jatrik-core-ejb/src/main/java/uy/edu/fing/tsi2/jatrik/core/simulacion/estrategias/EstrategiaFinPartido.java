package uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias;


import javax.ejb.Stateless;
import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;


@Stateless
public class EstrategiaFinPartido extends EstrategiaSimulacion {
	private Logger log = Logger.getLogger(EstrategiaFinPartido.class);

	@Override
	public void manejarEvento(Partido partido) {
		log.info("##### Fin del Partido #####");
		

	}

	public EstrategiaFinPartido() {
		this.setPeso(0);
	}

}
