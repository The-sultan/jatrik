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
	private InfoJugador golero;
	private ArrayList<InfoJugador> defensas;
	private ArrayList<InfoJugador> mediocampistas;
	private ArrayList<InfoJugador> delanteros;
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
	public InfoJugador getGolero() {
		return golero;
	}
	public void setGolero(InfoJugador golero) {
		this.golero = golero;
	}
	public ArrayList<InfoJugador> getDefensas() {
		return defensas;
	}
	public void setDefensas(ArrayList<InfoJugador> defensas) {
		this.defensas = defensas;
	}
	public ArrayList<InfoJugador> getMediocampistas() {
		return mediocampistas;
	}
	public void setMediocampistas(ArrayList<InfoJugador> mediocampistas) {
		this.mediocampistas = mediocampistas;
	}
	public ArrayList<InfoJugador> getDelanteros() {
		return delanteros;
	}
	public void setDelanteros(ArrayList<InfoJugador> delanteros) {
		this.delanteros = delanteros;
	}
	
	
	
	
}
