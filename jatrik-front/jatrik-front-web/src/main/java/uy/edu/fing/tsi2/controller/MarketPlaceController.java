package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import uy.edu.fing.tsi2.front.ejb.interfaces.TransferenciaEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferencia;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;

@SuppressWarnings("serial")
@Model
@RequestScoped
public class MarketPlaceController implements Serializable {

	@Inject
	SessionBeanJatrik sessionBean;

	@EJB
	private TransferenciaEJBLocal transferenciaEJB;
	
	public void venderJugador(Long idJugador, Double precio) {
		transferenciaEJB.ponerEnVentaJugador(this.sessionBean
				.getInfoUsuario().getInfoEquipo().getId(), idJugador, precio);
	}

	public void comprarJugador(Long idTransferencia) {
		transferenciaEJB.comprarJugador(this.sessionBean.getInfoUsuario()
				.getInfoEquipo().getId(), idTransferencia);
	}

	public List<InfoTransferencia> obtenerJugadoresEnVenta() {
		return transferenciaEJB.getTransferencias();
	}

	public SessionBeanJatrik getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBeanJatrik sessionBean) {
		this.sessionBean = sessionBean;
	}

	
}