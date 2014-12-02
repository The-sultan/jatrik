package uy.edu.fing.tsi2.jatrik.android.extras;

import java.util.List;

import android.app.Application;

public class InfoEquipo extends Application{

	private Long id;
	
	private String nombre;
	
	private Double fondos;
	
	private InfoEstadio estadio;
	
	//La formacion por defecto esta implicita en la cantidad de defensas, 
	//mediocampistas y delanteros titulares
	//En caso de que defensas.count=4, mediocampistas.count=3, delanteros.count=3
	//Entonces la formacion es 4-3-3
	private List<InfoJugador> jugadores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public List<InfoJugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<InfoJugador> jugadores) {
		this.jugadores = jugadores;
	}

	public void setNombre(String Nombre) {
		this.nombre = Nombre;
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