package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Farid
 */
@Entity
@Table(name="JUGADOR_FORMACION")
public class JugadorEnFormacion implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Jugador jugador;
	
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

	public JugadorEnFormacion() {
	}

	public JugadorEnFormacion(Jugador jugador, int indice) {
		this.jugador = jugador;
		this.indice = indice;
	}

	
	
}
