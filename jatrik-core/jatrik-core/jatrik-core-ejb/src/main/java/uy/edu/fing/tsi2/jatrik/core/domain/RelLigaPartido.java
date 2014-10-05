package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "FIXTURE")
public class RelLigaPartido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private IdRelLigaPartido id = new IdRelLigaPartido();

	@ManyToOne(targetEntity = Liga.class)
	@JoinColumn(name = "LIGA_ID", insertable = false, updatable = false)
	private Liga liga;	
	
	@ManyToOne(targetEntity = Partido.class)
	@JoinColumn(name = "PARTIDO_ID", insertable = false, updatable = false)
	private Partido partido;
	
	public RelLigaPartido () {
		super();
	}
	
	public RelLigaPartido (Liga liga, Partido partido) {
		this.liga = liga;
		this.partido = partido;
		
		this.id.setLigaId(liga.getId());
		this.id.setPartidoId(partido.getId());
		
		
	}

	
	
	public IdRelLigaPartido getId() {
		return id;
	}

	public void setId(IdRelLigaPartido id) {
		this.id = id;
	}

	public Liga getLiga() {
		return liga;
	}

	public void setLiga(Liga liga) {
		this.liga = liga;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
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
		if (!(object instanceof RelLigaPartido)) {
			return false;
		}
		RelLigaPartido other = (RelLigaPartido) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.RelLigaPartido[ id=" + id + " ]";
	}

	
}
