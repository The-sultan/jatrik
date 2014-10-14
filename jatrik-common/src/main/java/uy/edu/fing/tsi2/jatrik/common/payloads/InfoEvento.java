package uy.edu.fing.tsi2.jatrik.common.payloads;

/**
 * @author Farid
 */

public class InfoEvento {
	private int minuto;
	private String descripcion;

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public InfoEvento(int minuto, String descripcion) {
		this.minuto = minuto;
		this.descripcion = descripcion;
	}
	
	
}
