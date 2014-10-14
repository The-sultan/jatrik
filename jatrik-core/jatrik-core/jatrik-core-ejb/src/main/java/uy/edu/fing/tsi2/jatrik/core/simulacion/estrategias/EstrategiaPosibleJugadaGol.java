package uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias;

import javax.ejb.Stateless;
import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;

@Stateless
public class EstrategiaPosibleJugadaGol extends EstrategiaSimulacion{

	private Logger log = Logger.getLogger(EstrategiaPosibleJugadaGol.class);

	public EstrategiaPosibleJugadaGol() {
		this.setPeso(0);
	}
	
	
	@Override
	public void manejarEvento(Partido partido) {
		//por ahora, como no tenemos clara la probabilidad de jugada de gol,
		//siempre que se utiliza esta estrategia generamos una jugada de gol.
		
	}

}
