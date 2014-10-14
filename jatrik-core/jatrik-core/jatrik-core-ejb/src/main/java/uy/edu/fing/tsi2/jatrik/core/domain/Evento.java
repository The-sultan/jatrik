package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@NamedQueries({
	@NamedQuery(name="findEventoByName",query="SELECT OBJECT(e) FROM Evento e WHERE e.nombre = :nombre ")
	
})
@Entity
@Table(name = "EVENTOS")
public class Evento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2547064612558152347L;

	/**
	 * 
	 */
	@Id
	@SequenceGenerator(name = "SEQ_EVENTOS", sequenceName = "SEQ_EVENTOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EVENTOS")
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "COMENTARIO")
	private String comentario;

	@Column
	private String nombre;
	
	public Evento() {
		super();
	}

	public Evento(String comentario) {
		super();
		this.comentario = comentario;
	}

		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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
		if (!(object instanceof Evento)) {
			return false;
		}
		Evento other = (Evento) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.Evento[ id=" + id + " ]";
	}
		
}
