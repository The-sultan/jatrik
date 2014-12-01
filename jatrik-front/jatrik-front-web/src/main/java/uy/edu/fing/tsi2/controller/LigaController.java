package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.interfaces.LigaEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoLiga;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoPartido;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;
import uy.edu.fing.tsi2.navigation.AjaxNavigator;

@SuppressWarnings("serial")
@Named
@ManagedBean
@SessionScoped
public class LigaController implements Serializable {

	@EJB
	LigaEJBLocal ligaEJB;

	@Inject
	SessionBeanJatrik sessionBean;
	
	@Inject
	AjaxNavigator nav;
	
	@Inject
	PartidoController partidoController;

	@EJB
	EquipoEJBLocal equipoEJB;

	@Named
	private InfoLiga infoLiga;

	private List<Integer> fechas;

	private int fechaSeleccionada;
	
	private List<InfoPartido> partidosFecha;

	@PostConstruct
	public void initDatos() {
		infoLiga = ligaEJB.obtenerInfoLiga(sessionBean.getInfoUsuario()
				.getInfoEquipo().getId());
		setFechas(new ArrayList<Integer>(infoLiga.getPartidos().keySet()));
		setFechaSeleccionada(fechas.get(0));
		
		partidosFecha = infoLiga.getPartidos().get(fechaSeleccionada);
	}

	public void avanzarFecha() {
		fechaSeleccionada = ((fechaSeleccionada) % fechas.size()) + 1;
		partidosFecha = infoLiga.getPartidos().get(fechaSeleccionada);
	}

	public void retrocederFecha() {
		fechaSeleccionada = ((fechaSeleccionada) % fechas.size()) - 1;
		if(fechaSeleccionada<=0)
			fechaSeleccionada = fechas.size();
		partidosFecha = infoLiga.getPartidos().get(fechaSeleccionada);
	}
	
	
	public void navegarAPartido(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String partidoId =  params.get("partidoId"); 
		
		partidoController.setIdPartido(Integer.parseInt(partidoId));
		
		nav.setContent("partido");
		
	}
	
	public void actualizar(){
		infoLiga = ligaEJB.obtenerInfoLiga(sessionBean.getInfoUsuario()
				.getInfoEquipo().getId());
		setFechas(new ArrayList<Integer>(infoLiga.getPartidos().keySet()));
		
		partidosFecha = infoLiga.getPartidos().get(fechaSeleccionada);
	}
	

	public InfoLiga getInfoLiga() {
		return infoLiga;
	}

	public void setInfoLiga(InfoLiga infoLiga) {
		this.infoLiga = infoLiga;
	}

	public List<Integer> getFechas() {
		return fechas;
	}

	public void setFechas(List<Integer> fechas) {
		this.fechas = fechas;
	}

	public int getFechaSeleccionada() {
		return fechaSeleccionada;
	}

	public void setFechaSeleccionada(int fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}

	public List<InfoPartido> getPartidosFecha() {
		return partidosFecha;
	}

	public void setPartidosFecha(List<InfoPartido> partidosFecha) {
		this.partidosFecha = partidosFecha;
	}
}
