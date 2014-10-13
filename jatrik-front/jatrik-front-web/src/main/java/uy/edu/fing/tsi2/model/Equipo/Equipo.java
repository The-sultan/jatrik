package uy.edu.fing.tsi2.model.Equipo;

import java.util.ArrayList;

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

	private ArrayList<InfoJugador> titulares;
	private ArrayList<InfoJugador> suplentes;
	private ArrayList<InfoJugador> reserva;
	
	
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
	public ArrayList<InfoJugador> getTitulares() {
		return titulares;
	}
	public void setTitulares(ArrayList<InfoJugador> titulares) {
		this.titulares = titulares;
	}
	public ArrayList<InfoJugador> getSuplentes() {
		return suplentes;
	}
	public void setSuplentes(ArrayList<InfoJugador> suplentes) {
		this.suplentes = suplentes;
	}
	public ArrayList<InfoJugador> getReserva() {
		return reserva;
	}
	public void setReserva(ArrayList<InfoJugador> reserva) {
		this.reserva = reserva;
	}

}
