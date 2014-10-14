package uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias;

import javax.ejb.Stateless;
import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;

@Stateless
public class EstrategiaLesion extends EstrategiaSimulacion{

	public EstrategiaLesion() {
		this.setPeso(0);
	}
	
	private Logger log = Logger.getLogger(EstrategiaLesion.class);
	
	@Override
	public void manejarEvento(Partido partido) {
		
		log.info("##### Se lesiono uno #####");
	}

}
