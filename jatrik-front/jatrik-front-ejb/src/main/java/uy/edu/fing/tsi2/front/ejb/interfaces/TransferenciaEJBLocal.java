package uy.edu.fing.tsi2.front.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferencia;

@Local
public abstract interface TransferenciaEJBLocal {
	
	public abstract List<InfoTransferencia> getTransferencias();

	public abstract void ponerEnVentaJugador(Long paramLong1, Long paramLong2,
			Double paramDouble);

	public abstract void comprarJugador(Long paramLong1, Long paramLong2);
	
	public List<InfoTransferencia> getTransferencias(Long id);
	
	
}
