package uy.edu.fing.tsi2.jatrik.common.payloads;

import java.util.ArrayList;

public class InfoEquipo {

	private int id;
	
	private String Nombre;
	
	private Double fondos;
	
	private InfoEstadio estadio;
	
	//La formacion por defecto esta implicita en la cantidad de defensas, 
	//mediocampistas y delanteros titulares
	//En caso de que defensas.count=4, mediocampistas.count=3, delanteros.count=3
	//Entonces la formacion es 4-3-3
	private InfoJugador golero;
	private ArrayList<InfoJugador> defensas;
	private ArrayList<InfoJugador> mediocampistas;
	private ArrayList<InfoJugador> delanteros;
	
	
	
	
	
}
