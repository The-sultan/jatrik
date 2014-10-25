package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Transferencia;
import uy.edu.fing.tsi2.jatrik.core.ejb.ITransferencias;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerTransferenciaBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerTransferenciaBeanRemote;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMTransferenciasLocal;

@Stateless
@Local(EJBManagerTransferenciaBeanLocal.class)
@Remote(EJBManagerTransferenciaBeanRemote.class)
public class EJBManagerTransferenciaBean implements ITransferencias {
	
	private static final Logger logger = Logger
			.getLogger(EJBManagerTransferenciaBean.class);
	
	@EJB
	private EJBEMTransferenciasLocal transferencias;
	
	@EJB
	private EJBEMJugadoresLocal jugadores;
			
	@EJB
	private EJBEMEquiposLocal equipos;
	
	public Transferencia addTransferencia(Transferencia transferencia) {
		transferencias.add(transferencia);
		return transferencia;
	}

	
	public void deleteTransferencia(Transferencia transferencia) {
		transferencias.delete(transferencia);
	}

	
	public Transferencia findTransferencia(Long id) {
		return transferencias.find(id);
	}

	
	public Transferencia updateTransferencia(Transferencia transferencia) {
		transferencia = transferencias.update(transferencia);
		return transferencia;
	}
	
	public List<Transferencia> findAllTransferencia(){
		
		return transferencias.findAll();
	} 
	
	public void ponerJugadorEnVenta(Long jugadorId, Long vendedorId,Double precio) {
		
				
		Jugador jugador = jugadores.find(jugadorId);
		Equipo equipo = jugador.getEquipo();
		
		jugador.setEnVenta(new Boolean(true));		
		jugadores.update(jugador);
		
		Transferencia transferencia = new Transferencia(jugador,equipo,precio,null);
		
		transferencias.add(transferencia);		
	}
	
	
	public boolean comprarJugador(Long transferenciaId, Long CompradorId) {

		// ToDo deberiamos controlar que el euipo comprador tenga la plata para
		// comprarlo
		Transferencia trans = transferencias.find(transferenciaId);
		Equipo equipoCompra = equipos.find(CompradorId);
		Equipo equipoVende = trans.getVendedor();
		Jugador jugador = trans.getJugador();
		Double diferencia = equipoCompra.getFondos() - trans.getPrecio();
		if (diferencia >= 0) {
			logger.info("Se vende el jugador :"
					+ trans.getJugador().getNombre());
			equipoVende.getJugadores().remove(jugador);
			equipoVende.setFondos(equipoVende.getFondos() + trans.getPrecio());

			equipoCompra.getJugadores().add(jugador);
			equipoCompra.setFondos(diferencia);

			trans.setComprador(equipoCompra);

			equipos.update(equipoCompra);
			equipos.update(equipoVende);

			jugador.setEquipo(equipoCompra);
			jugador.setEnVenta(new Boolean(false));
			jugadores.update(jugador);

			transferencias.update(trans);

			return true;
		}
		return false;
	}
}
