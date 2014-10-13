package uy.edu.fing.tsi2.model.Equipo;

import java.util.List;

import javax.inject.Named;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;

@Named
public class Equipo {
	private String nombre;
	
	private String formacion;
	
	//La formacion por defecto esta implicita en la cantidad de defensas, 
	//mediocampistas y delanteros titulares
	//En caso de que defensas.count=4, mediocampistas.count=3, delanteros.count=3
	//Entonces la formacion es 4-3-3

	private List<InfoJugador> titulares;
	private List<InfoJugador> suplentes;
	private List<InfoJugador> reserva;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFormacion() {
		return formacion;
	}
	public void setFormacion(String formacion) {
		this.formacion = formacion;
	}
	public List<InfoJugador> getTitulares() {
		return titulares;
	}
	public void setTitulares(List<InfoJugador> titulares) {
		this.titulares = titulares;
	}
	public List<InfoJugador> getSuplentes() {
		return suplentes;
	}
	public void setSuplentes(List<InfoJugador> suplentes) {
		this.suplentes = suplentes;
	}
	public List<InfoJugador> getReserva() {
		return reserva;
	}
	public void setReserva(List<InfoJugador> reserva) {
		this.reserva = reserva;
	}

}
