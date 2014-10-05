package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdRelLigaPartido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9122319818712650051L;

	@Column(name = "LIGA_ID")
	private Long ligaId;

	@Column(name = "PARTIDO_ID")
	private Long partidoId;

	public IdRelLigaPartido() {
		super();
	}

	public IdRelLigaPartido(Long ligaId, Long partidoId) {
		super();
		this.ligaId = ligaId;
		this.partidoId = partidoId;
	}

	public Long getLigaId() {
		return ligaId;
	}

	public void setLigaId(Long ligaId) {
		this.ligaId = ligaId;
	}

	public Long getPartidoId() {
		return partidoId;
	}

	public void setPartidoId(Long partidoId) {
		this.partidoId = partidoId;
	}
	
	

	@Override
	public int hashCode() {
		int result = 1;
		result = result + ((ligaId == null) ? 0 : ligaId.hashCode());
		result = result + ((partidoId == null) ? 0 : partidoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (null == obj)
			return false;
		if (obj instanceof IdRelLigaPartido) {
			IdRelLigaPartido idAux = (IdRelLigaPartido) obj;
			if (ligaId == null) {
				if (idAux.ligaId != null)
					return false;
			} else if (!ligaId.equals(idAux.ligaId))
				return false;
			if (partidoId == null) {
				if (idAux.partidoId != null)
					return false;
			} else if (!partidoId.equals(idAux.partidoId))
				return false;

			return true;
		}

		return false;
	}

}
