package uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias;


import javax.ejb.Stateless;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;


@Stateless
public class EstrategiaIdle extends EstrategiaSimulacion {
	@Override
	public void manejarEvento(Partido partido) {
		
	}

	public EstrategiaIdle() {
		this.setPeso(50);
	}

}
