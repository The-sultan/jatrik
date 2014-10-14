package uy.edu.fing.tsi2.jatrik.core.domain.eventos;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import uy.edu.fing.tsi2.jatrik.core.domain.Comentario;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;

@Entity
@DiscriminatorValue(value = "3")
public class EventoPartidoLesion extends EventoPartido {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2667783613146078587L;



	public EventoPartidoLesion(Integer minuto, Partido partido, Evento evento, Long nivel, Jugador jugador, Equipo equipo) {
		super(minuto, partido, evento);
		this.nivel = nivel;
		this.jugador = jugador;
		this.equipo = equipo;
	}

	@ManyToOne
	private Comentario comentario;
	
	
	@ManyToOne(targetEntity = Jugador.class)
	@JoinColumn(name = "JUGADOR_ID", referencedColumnName = "ID")
	private Jugador jugador;


	@ManyToOne(targetEntity = Equipo.class)
	@JoinColumn(name = "EQUIPO_ID", referencedColumnName = "ID")
	private Equipo equipo;
	
	@Column
	private Long nivel;

	
	public EventoPartidoLesion(){
		super();
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

	public Long getNivel() {
		return nivel;
	}

	public void setNivel(Long nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString(){
		return String.format(this.getComentario().getDescripcion(),this.getJugador().getNombre());
	}
	
}
