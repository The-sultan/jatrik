package uy.edu.fing.tsi2.jatrik.common.payloads;

import java.util.ArrayList;

public class InfoJugador {
	
	private Long id;

	private String nombre;

	private int edad;

	private double altura;

	private double peso;

	private String puesto;

	private Boolean enVenta;

	private int nroCamiseta;

	private ArrayList<InfoHabilidad> habilidades;
	
	
	

	public class InfoHabilidad{
		private int id;
		private String Nombre;
		private int valor;
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNombre() {
			return Nombre;
		}
		public void setNombre(String nombre) {
			Nombre = nombre;
		}
		public int getValor() {
			return valor;
		}
		public void setValor(int valor) {
			this.valor = valor;
		}
	}
}

