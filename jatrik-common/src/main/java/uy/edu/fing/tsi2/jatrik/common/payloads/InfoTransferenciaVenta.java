package uy.edu.fing.tsi2.jatrik.common.payloads;

public class InfoTransferenciaVenta {
	private Long idEquipoVende;
	private Long idJugador;
	private Double precio;

	public Long getIdEquipoVende() {
		return this.idEquipoVende;
	}

	public void setIdEquipoVende(Long idEquipoVende) {
		this.idEquipoVende = idEquipoVende;
	}

	public Long getIdJugador() {
		return this.idJugador;
	}

	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
	}

	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
}