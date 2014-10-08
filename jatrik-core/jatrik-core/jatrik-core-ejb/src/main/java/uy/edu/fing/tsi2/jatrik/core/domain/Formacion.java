package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 *
 * @author Farid
 */

@Entity
@Table(name="FORMACIONES")
public class Formacion implements Serializable{
	
	@Id
	private Long id;

	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("indice")
	private Set<JugadorEnFormacion> defensas;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("indice")
	private Set<JugadorEnFormacion> mediocampistas;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("indice")
	private Set<JugadorEnFormacion> delanteros;
	
	@OneToMany(fetch=FetchType.LAZY)
	private Set<Jugador> jugadoresSuplentes;
	
	@OneToMany(fetch=FetchType.LAZY)
	private Set<Jugador> jugadoresReserva;

	@Column
	private Jugador arquero;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<JugadorEnFormacion> getDefensas() {
		return defensas;
	}

	public void setDefensas(Set<JugadorEnFormacion> defensas) {
		this.defensas = defensas;
	}

	public Set<JugadorEnFormacion> getMediocampistas() {
		return mediocampistas;
	}

	public void setMediocampistas(Set<JugadorEnFormacion> mediocampistas) {
		this.mediocampistas = mediocampistas;
	}

	public Set<JugadorEnFormacion> getDelanteros() {
		return delanteros;
	}

	public void setDelanteros(Set<JugadorEnFormacion> delanteros) {
		this.delanteros = delanteros;
	}

	

	public Set<Jugador> getJugadoresSuplentes() {
		return jugadoresSuplentes;
	}

	public void setJugadoresSuplentes(Set<Jugador> jugadoresSuplentes) {
		this.jugadoresSuplentes = jugadoresSuplentes;
	}

	public Set<Jugador> getJugadoresReserva() {
		return jugadoresReserva;
	}

	public void setJugadoresReserva(Set<Jugador> jugadoresReserva) {
		this.jugadoresReserva = jugadoresReserva;
	}

	public Jugador getArquero() {
		return arquero;
	}

	public void setArquero(Jugador arquero) {
		this.arquero = arquero;
	}
	
	
	public Formacion() {
	}

}
