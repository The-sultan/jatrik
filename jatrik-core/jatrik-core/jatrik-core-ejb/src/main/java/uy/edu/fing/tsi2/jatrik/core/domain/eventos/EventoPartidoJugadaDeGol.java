package uy.edu.fing.tsi2.jatrik.core.domain.eventos;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import uy.edu.fing.tsi2.jatrik.core.domain.Comentario;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;

@Entity
@DiscriminatorValue(value = "2")
public class EventoPartidoJugadaDeGol extends EventoPartido {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2667783613146078587L;

	public EventoPartidoJugadaDeGol() {
		super();
	}



	public EventoPartidoJugadaDeGol(Integer minuto, Partido partido, Evento evento, int nivel, Jugador jugador, Equipo equipo) {
		super(minuto, partido, evento);
		this.nivel = nivel;
		this.jugador = jugador;
		this.equipo = equipo;
	}

	@Column
	private int nivel;

	@ManyToOne(targetEntity = Jugador.class)
	@JoinColumn(name = "JUGADOR_ID", insertable = false, updatable = false)
	private Jugador jugador;
	
	@ManyToOne(targetEntity = Equipo.class)
	@JoinColumn(name = "EQUIPO_ID", insertable = false, updatable = false)
	private Equipo equipo;
	
	@ManyToOne
	private Comentario comentario;
	

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	
	public String toString(){
		return String.format(this.getComentario().getDescripcion(), this.getJugador().getNombre());
	}
	
	
	

}
