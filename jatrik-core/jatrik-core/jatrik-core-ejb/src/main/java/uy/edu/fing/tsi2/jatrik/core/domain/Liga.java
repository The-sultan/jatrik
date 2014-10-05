package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LIGAS")
public class Liga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3474209761703880892L;

	@Id
	@SequenceGenerator(name = "SEQ_LIGA", sequenceName = "SEQ_LIGA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LIGA")
	@Column(name = "ID")
	private Long id;

	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;

	@Column(name = "FECHA_FIN")
	private Date fechaFin;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@OrderBy("ptos, diferencia, golesAFavor ASC")
	@OneToMany(targetEntity = RelLigaEquipo.class, mappedBy = "liga",
				fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Set<RelLigaEquipo> relLigaEquipo = new HashSet<RelLigaEquipo>();

	@OneToMany(targetEntity = RelLigaPartido.class, mappedBy = "liga", 
			  fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Set<RelLigaPartido> relLigaPartido = new HashSet<RelLigaPartido>();
	
	
	public Liga() {
		super();
	}
	
	public Liga (String descripcion, Date fechaInicio, Date fechaFin) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.relLigaPartido = new HashSet<RelLigaPartido>();
		this.relLigaEquipo = new HashSet<RelLigaEquipo>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Set<RelLigaEquipo> getRelLigaEquipo() {
		return relLigaEquipo;
	}

	public void setRelLigaEquipo(Set<RelLigaEquipo> relLigaEquipo) {
		this.relLigaEquipo = relLigaEquipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	

	public Set<RelLigaPartido> getRelLigaPartido() {
		return relLigaPartido;
	}

	public void setRelLigaPartido(Set<RelLigaPartido> relLigaPartido) {
		this.relLigaPartido = relLigaPartido;
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
		if (!(object instanceof Liga)) {
			return false;
		}
		Liga other = (Liga) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.Liga[ id=" + id + " ]";
	}

}
