package uy.edu.fing.tsi2.jatrik.common.payloads;

import java.util.List;

public class InfoPartido {

	private Long idPartido;
	
	private String estado;
	
	private int golesLocal;

	private int golesVisitante;

	private String equipoLocal;

	private String equipoVisitante;

	
	
	private List<InfoEvento> eventos;

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}	
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public String getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(String equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public String getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(String equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}
	
	public List<InfoEvento> getEventos() {
		return eventos;
	}

	public void setEventos(List<InfoEvento> eventos) {
		this.eventos = eventos;
	}
	
	public InfoPartido (Long id, String Estado, String Local, String Visitante, int golesL, int golesV){
		idPartido = id;
		estado = Estado;
		equipoLocal = Local;
		equipoVisitante = Visitante;
		golesLocal = golesL;
		golesVisitante = golesV;
	}

}
