package uy.edu.fing.tsi2.model.Equipo;

import java.util.ArrayList;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;

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
	
	
}
