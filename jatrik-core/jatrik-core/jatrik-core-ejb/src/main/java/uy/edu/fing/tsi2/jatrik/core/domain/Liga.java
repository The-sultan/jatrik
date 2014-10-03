package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;


@Entity
@Table(name="LIGAS")
public class Liga implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3474209761703880892L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="FECHA_INICIO")
	private Calendar fechaInicio;
	
	@Column(name="FECHA_FIN")
	private Calendar fechaFin;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
		
	public Liga() {
		super();
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Calendar getFechaInicio() {
		return fechaInicio;
	}



	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}



	public Calendar getFechaFin() {
		return fechaFin;
	}



	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Liga)) {
			return false;
		}
		Liga other = (Liga) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.Liga[ id=" + id + " ]";
	}
   
}
