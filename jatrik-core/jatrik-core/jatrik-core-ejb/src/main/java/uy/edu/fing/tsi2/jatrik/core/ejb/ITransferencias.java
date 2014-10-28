package uy.edu.fing.tsi2.jatrik.core.ejb;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.Transferencia;


public interface ITransferencias {
	
	boolean ponerJugadorEnVenta(Long jugadorId, Long vendedorId,Double precio);
	
	boolean comprarJugador(Long transferenciaId, Long CompradorId);
	
	// listado de transferencias de jugadores en venta que no son del equipo idEquipo
	List<Transferencia> listadoJugadoresEnVenta(Long idEquipo);
	
}
