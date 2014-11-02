package uy.edu.fing.tsi2.jatrik.common.payloads;

public class InfoTransferenciaCompra {
	
	private Long idEquipoCompra;
	private Long idTransferencia;

	public Long getIdEquipoCompra() {
		return this.idEquipoCompra;
	}

	public void setIdEquipoCompra(Long idEquipoCompra) {
		this.idEquipoCompra = idEquipoCompra;
	}

	public Long getIdTransferencia() {
		return this.idTransferencia;
	}

	public void setIdTransferencia(Long idTransferencia) {
		this.idTransferencia = idTransferencia;
	}
}