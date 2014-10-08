package uy.edu.fing.tsi2.model.Equipo;

import java.util.ArrayList;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador.InfoHabilidad;

public class Jugador {

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




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public int getEdad() {
		return edad;
	}




	public void setEdad(int edad) {
		this.edad = edad;
	}




	public double getAltura() {
		return altura;
	}




	public void setAltura(double altura) {
		this.altura = altura;
	}




	public double getPeso() {
		return peso;
	}




	public void setPeso(double peso) {
		this.peso = peso;
	}




	public String getPuesto() {
		return puesto;
	}




	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}




	public Boolean getEnVenta() {
		return enVenta;
	}




	public void setEnVenta(Boolean enVenta) {
		this.enVenta = enVenta;
	}




	public int getNroCamiseta() {
		return nroCamiseta;
	}




	public void setNroCamiseta(int nroCamiseta) {
		this.nroCamiseta = nroCamiseta;
	}




	public ArrayList<InfoHabilidad> getHabilidades() {
		return habilidades;
	}




	public void setHabilidades(ArrayList<InfoHabilidad> habilidades) {
		this.habilidades = habilidades;
	}
	
}
