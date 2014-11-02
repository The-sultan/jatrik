package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Farid
 */

@Entity
@Table(name="FORMACIONES")
public class Formacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1104456436729331947L;

	@Id
	@SequenceGenerator(name="SEQ_FORMACIONES",sequenceName="SEQ_FORMACIONES",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_FORMACIONES")
	private Long id;

	@OrderBy("puestoformacion, indice")
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, 
			targetEntity = JugadorEnFormacion.class, mappedBy = "formacion")
	private Set<JugadorEnFormacion> jugadores;
	
	private String descriptor;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<JugadorEnFormacion> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<JugadorEnFormacion> jugadores) {
		this.jugadores = jugadores;
	}

	public Formacion() {
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	
}
