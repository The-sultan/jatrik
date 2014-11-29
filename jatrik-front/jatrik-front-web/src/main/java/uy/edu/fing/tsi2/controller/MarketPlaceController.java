package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.interfaces.TransferenciaEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferencia;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;

@SuppressWarnings("serial")
@Model
@SessionScoped
public class MarketPlaceController implements Serializable {

	@Inject
	SessionBeanJatrik sessionBean;

	private List<InfoTransferencia> transferencias;

	@EJB
	private TransferenciaEJBLocal transferenciaEJB;

	@EJB
	private EquipoEJBLocal equipoEJB;

	private InfoJugador jugadorSeleccionadoVenta;
	private double valorVenta;

	private InfoTransferencia transferenciaSeleccionadaCompra;

	private List<InfoJugador> jugadoresEnVenta;

	private List<InfoJugador> jugadoresNoEnVenta;

	private Long idEquipo;

	private boolean error = false;

	@PostConstruct
	public void obtenerJugadoresEnVenta() {

		transferencias = transferenciaEJB.getTransferencias(sessionBean
				.getInfoUsuario().getId());

		jugadoresNoEnVenta = equipoEJB.getJugadoresEquipo(sessionBean
				.getInfoUsuario().getInfoEquipo().getId());

		jugadoresEnVenta = new ArrayList<InfoJugador>();
		for (InfoTransferencia infoTransferencia : transferencias) {
			jugadoresEnVenta.add(infoTransferencia.getJugador());
		}

		// Esto no funciona porque son listas que contienen diferentes tipos
		// Por ahora sigo con la lista de jugadores completa para vender
		// jugadoresNoEnVenta.removeAll(transferencias);

		jugadorSeleccionadoVenta = jugadoresNoEnVenta.get(0);
		
		if(jugadoresEnVenta.size() > 0){
			transferenciaSeleccionadaCompra = transferencias.get(0);
			
		}

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

	public void seleccionarJugadorVenta() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		int id = Integer.parseInt(params.get("id"));

		for (InfoJugador infoJugador : jugadoresNoEnVenta) {
			if (infoJugador.getId() == id) {
				jugadorSeleccionadoVenta = infoJugador;
				break;
			}
		}

		for (InfoTransferencia transferencia : transferencias) {
			if (transferencia.getJugador().getId() == id) {
				transferenciaSeleccionadaCompra = transferencia;
				break;
			}
		}

	}

	public void compraDeJugador() {
		try {
			if(transferenciaSeleccionadaCompra.getPrecio() > sessionBean.getInfoUsuario().getInfoEquipo().getFondos()){
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Atencion",
						"El equipo no tiene fondos suficientes para comrpar el jugador"));
				return;
			}
			
			comprarJugador(transferenciaSeleccionadaCompra.getId());
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Felicitaciones",
					"Se ha concretado la compra"));
			obtenerJugadoresEnVenta();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Error",
					"Ocurrio un error al realizar la compra"));
		}
		
	}

	public void venderJugador() {
		try {
			if (valorVenta <= 0) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Error",
						"Debe seleccionar un valor mayor a cero"));
				return;
			}

			transferenciaEJB.ponerEnVentaJugador(this.sessionBean
					.getInfoUsuario().getInfoEquipo().getId(),
					jugadorSeleccionadoVenta.getId(), valorVenta);
			obtenerJugadoresEnVenta();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Venta realizada",
					"El jugador fue puesto en venta"));
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Error",
					"Ocurrio un error al realizar la publicacion"));
		}

	}

	public List<InfoTransferencia> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(List<InfoTransferencia> transferencias) {
		this.transferencias = transferencias;
	}

	public Long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public InfoJugador getJugadorSeleccionadoVenta() {
		return jugadorSeleccionadoVenta;
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

	public List<InfoJugador> getJugadoresEnVenta() {
		return jugadoresEnVenta;
	}

	public void setJugadoresEnVenta(List<InfoJugador> jugadoresEnVenta) {
		this.jugadoresEnVenta = jugadoresEnVenta;
	}

	public double getValorVenta() {
		return valorVenta;
	}

	public void setValorVenta(double valorVenta) {
		this.valorVenta = valorVenta;
	}

	public InfoTransferencia getTransferenciaSeleccionadaCompra() {
		return transferenciaSeleccionadaCompra;
	}

	public void setTransferenciaSeleccionadaCompra(
			InfoTransferencia transferenciaSeleccionadaCompra) {
		this.transferenciaSeleccionadaCompra = transferenciaSeleccionadaCompra;
	}

}