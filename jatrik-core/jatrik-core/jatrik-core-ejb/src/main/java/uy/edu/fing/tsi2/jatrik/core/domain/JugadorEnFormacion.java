package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoFormacion;

/**
 * @author Farid
 */
@Entity
@Table(name="JUGADOR_FORMACION")
public class JugadorEnFormacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -624425669894529429L;

	@Id
	@SequenceGenerator(name="SEQ_JUGADOR_FORMACION",sequenceName="SEQ_JUGADOR_FORMACION",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_JUGADOR_FORMACION")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="JUGADOR_ID", referencedColumnName="ID")
	private Jugador jugador;
	
	@Column
	@Enumerated
	private EnumPuestoFormacion puestoFormacion;
	
	@Column
	private int indice;
	
	@ManyToOne(targetEntity = Formacion.class)
	@JoinColumn(name = "FORMACION_ID", referencedColumnName = "ID")
	private Formacion formacion;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Formacion getFormacion() {
		return formacion;
	}

	public void setFormacion(Formacion formacion) {
		this.formacion = formacion;
	}

	public EnumPuestoFormacion getPuestoFormacion() {
		return puestoFormacion;
	}

	public void setPuestoFormacion(EnumPuestoFormacion puestoFormacion) {
		this.puestoFormacion = puestoFormacion;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	
	public JugadorEnFormacion() {
	}

	public JugadorEnFormacion(Jugador jugador, int indice, EnumPuestoFormacion puesto,
	Formacion formacion) {
		this.jugador = jugador;
		this.indice = indice;
		this.puestoFormacion = puesto;
		this.formacion = formacion;
	}

	
	
}
