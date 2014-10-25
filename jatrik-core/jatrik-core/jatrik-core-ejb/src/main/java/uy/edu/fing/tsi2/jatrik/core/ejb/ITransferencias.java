package uy.edu.fing.tsi2.jatrik.core.ejb;


public interface ITransferencias {
	
	void ponerJugadorEnVenta(Long jugadorId, Long vendedorId,Double precio);
	
	boolean comprarJugador(Long transferenciaId, Long CompradorId);
}
