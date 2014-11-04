package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	
	private List<InfoJugador> misJugadores;
	
	@EJB
	private TransferenciaEJBLocal transferenciaEJB;
	
	@EJB
	private EquipoEJBLocal equipoEJB;
	
	private List<Long> compras;
	
		
	private Long idEquipo;
	
	@PostConstruct
	public void obtenerJugadoresEnVenta() {
		
		transferencias = transferenciaEJB.getTransferencias(sessionBean.getInfoUsuario().getId());
		setMisJugadores(equipoEJB.getJugadoresEquipo(sessionBean.getInfoUsuario().getId()));
					
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
		for (InfoTransferencia element : transferencias) {
			if (element.getComprar()){
				monto =+ element.getPrecio();
				compras.add(element.getId());
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
	
		for (InfoJugador element : misJugadores) {
			if (element.getSelectVenta()){
				venderJugador(element.getId(), element.getPrecio());
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

	
}