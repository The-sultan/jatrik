package uy.edu.fing.tsi2.jatrik.common.payloads;

import java.util.List;

public class InfoEquipo {

	private Long id;
	
	private String Nombre;
	
	private Double fondos;
	
	private InfoEstadio estadio;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}

	public Double getFondos() {
		return fondos;
	}

	public void setFondos(Double fondos) {
		this.fondos = fondos;
	}

	public InfoEstadio getEstadio() {
		return estadio;
	}

	public void setEstadio(InfoEstadio estadio) {
		this.estadio = estadio;
	}

}
