package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity(name="DATOS_JUGADOR")
public class DatosJugador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6888858098447319132L;

	@Id
	@SequenceGenerator(name="SEQ_DATOS_JUGADOR",sequenceName="SEQ_DATOS_JUGADOR",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_DATOS_JUGADOR")
	@Column(name="ID")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;
	
	public DatosJugador () {
		super();
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DatosJugador)) {
			return false;
		}
		DatosJugador other = (DatosJugador) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.DatosJugador[ id=" + id + " ]";
	}

}
