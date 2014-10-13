package uy.edu.fing.tsi2.jatrik.common.payloads;

import java.util.List;

public class InfoJugador {
	
	private Long id;

	private String nombre;

	private int edad;

	private double altura;

	private double peso;

	private String puesto;

	private Boolean enVenta;

	private int nroCamiseta;

	private List<InfoHabilidad> habilidades;

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

	public List<InfoHabilidad> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<InfoHabilidad> habilidades) {
		this.habilidades = habilidades;
	}
	

}

