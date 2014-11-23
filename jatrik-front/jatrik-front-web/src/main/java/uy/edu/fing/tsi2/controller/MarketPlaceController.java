package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.interfaces.TransferenciaEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferencia;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;

@SuppressWarnings("serial")
@Model
@RequestScoped
public class MarketPlaceController implements Serializable {

	@Inject
	SessionBeanJatrik sessionBean;
	
	private List<InfoTransferencia> transferencias;
	
	Map<InfoJugador, Boolean> selectedPlayersVenta = new HashMap<>();
	
	Map<InfoTransferencia, Boolean> selectedPlayersCompra = new HashMap<>();
	
	private List<InfoJugador> misJugadores;
	
	@EJB
	private TransferenciaEJBLocal transferenciaEJB;
	
	@EJB
	private EquipoEJBLocal equipoEJB;
	
	private List<Long> compras;
	
	private InfoJugador jugadorSeleccionadoVenta;
	
	private List<InfoJugador> jugadoresNoEnVenta;
	
		
	private Long idEquipo;
	
	private boolean error = false;
	
	@PostConstruct
	public void obtenerJugadoresEnVenta() {
		
		transferencias = transferenciaEJB.getTransferencias(sessionBean.getInfoUsuario().getId());
		List<InfoJugador> infoJugadores = equipoEJB.getJugadoresEquipo(sessionBean.getInfoUsuario().getId());
		setMisJugadores(infoJugadores);
		for(InfoJugador jugador : infoJugadores){
			selectedPlayersVenta.put(jugador,false);
		}
		for(InfoTransferencia infoTransferencia : transferencias){
			selectedPlayersCompra.put(infoTransferencia, Boolean.FALSE);
		}
		
		jugadoresNoEnVenta.addAll(infoJugadores);
		jugadoresNoEnVenta.removeAll(transferencias);
					
	}

	public void venderJugador(Long idJugador, Double precio) {
		transferenciaEJB.ponerEnVentaJugador(this.sessionBean
				.getInfoUsuario().getInfoEquipo().getId(), idJugador, precio);
	}

	private void comprarJugador(Long idTransferencia) {
		transferenciaEJB.comprarJugador(this.sessionBean.getInfoUsuario()
				.getInfoEquipo().getId(), idTransferencia);
	}

	
	public SessionBeanJatrik getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBeanJatrik sessionBean) {
		this.sessionBean = sessionBean;
	}

	
	public void compraDeJugadores(){
		compras = new ArrayList<Long>();
		Double monto = 0.0;
		for (InfoTransferencia transferencia : selectedPlayersCompra.keySet()) {
			if (selectedPlayersCompra.get(transferencia)){
				monto =+ transferencia.getPrecio();
				compras.add(transferencia.getId());
			}			
		}
		if (monto<= sessionBean.getInfoUsuario().getInfoEquipo().getFondos()){
			for (Long elem : compras) {
				comprarJugador(elem);
			}
		}else{
			//TODO VER COMO TIRO ERROR
		}
	}

	public void venderJugadores(){
	
		for (InfoJugador jugador : selectedPlayersVenta.keySet()) {
			if (selectedPlayersVenta.get(jugador)){
				venderJugador(jugador.getId(), jugador.getPrecio());
			}			
		}
	
	}
	
	public List<InfoTransferencia> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(List<InfoTransferencia> transferencias) {
		this.transferencias = transferencias;
	}

	public List<Long> getCompras() {
		return compras;
	}

	public void setCompras(List<Long> compras) {
		this.compras = compras;
	}

	public Long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public List<InfoJugador> getMisJugadores() {
		return misJugadores;
	}

	public void setMisJugadores(List<InfoJugador> misJugadores) {
		this.misJugadores = misJugadores;
	}

	public Map<InfoJugador, Boolean> getSelectedPlayersVenta() {
		return selectedPlayersVenta;
	}

	public void setSelectedPlayersVenta(Map<InfoJugador, Boolean> selectedPlayersVenta) {
		this.selectedPlayersVenta = selectedPlayersVenta;
	}

	public Map<InfoTransferencia, Boolean> getSelectedPlayersCompra() {
		return selectedPlayersCompra;
	}

	public void setSelectedPlayersCompra(Map<InfoTransferencia, Boolean> selectedPlayersCompra) {
		this.selectedPlayersCompra = selectedPlayersCompra;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public InfoJugador getJugadorSeleccionadoVenta() {
		//TODO: Implementar bien
		return misJugadores.get(1);
	}

	public void setJugadorSeleccionadoVenta(InfoJugador jugadorSeleccionadoVenta) {
		this.jugadorSeleccionadoVenta = jugadorSeleccionadoVenta;
	}

	public List<InfoJugador> getJugadoresNoEnVenta() {
		return jugadoresNoEnVenta;
	}

	public void setJugadoresNoEnVenta(List<InfoJugador> jugadoresNoEnVenta) {
		this.jugadoresNoEnVenta = jugadoresNoEnVenta;
	}

}