package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdRelLigaEquipo implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -7805193677226941429L;

	@Column(name = "LIGA_ID")
	private Long ligaId;

	@Column(name = "EQUIPO_ID")
	private Long equipoId;

	public IdRelLigaEquipo() {
		super();
	}

	public IdRelLigaEquipo(Long ligaId, Long equipoId) {
		super();
		this.ligaId = ligaId;
		this.equipoId = equipoId;
	}

	public Long getLigaId() {
		return ligaId;
	}

	public void setLigaId(Long ligaId) {
		this.ligaId = ligaId;
	}

	public Long getEquipoId() {
		return equipoId;
	}

	public void setEquipoId(Long equipoId) {
		this.equipoId = equipoId;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = result + ((ligaId == null) ? 0 : ligaId.hashCode());
		result = result + ((equipoId == null) ? 0 : equipoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (null == obj)
			return false;
		if (obj instanceof IdRelLigaEquipo) {
			IdRelLigaEquipo idAux = (IdRelLigaEquipo) obj;
			if (ligaId == null) {
				if (idAux.ligaId != null)
					return false;
			} else if (!ligaId.equals(idAux.ligaId))
				return false;
			if (equipoId == null) {
				if (idAux.equipoId != null)
					return false;
			} else if (!equipoId.equals(idAux.equipoId))
				return false;

			return true;
		}

		return false;
	}

}
