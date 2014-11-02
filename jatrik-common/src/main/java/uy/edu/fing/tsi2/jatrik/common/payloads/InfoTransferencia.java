package uy.edu.fing.tsi2.jatrik.common.payloads;

public class InfoTransferencia {
	
	private Long id;
	private InfoJugador jugador;
	private Double precio;
	private Long equipoIdVendedor;
	private Long equipoIdComprador;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InfoJugador getJugador() {
		return this.jugador;
	}

	public void setJugador(InfoJugador jugador) {
		this.jugador = jugador;
	}

	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Long getEquipoIdVendedor() {
		return this.equipoIdVendedor;
	}

	public void setEquipoIdVendedor(Long equipoIdVendedor) {
		this.equipoIdVendedor = equipoIdVendedor;
	}

	public Long getEquipoIdComprador() {
		return this.equipoIdComprador;
	}

	public void setEquipoIdComprador(Long equipoIdComprador) {
		this.equipoIdComprador = equipoIdComprador;
	}
}