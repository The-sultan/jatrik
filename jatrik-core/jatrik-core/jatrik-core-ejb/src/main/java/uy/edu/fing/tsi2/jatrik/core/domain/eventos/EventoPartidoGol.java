package uy.edu.fing.tsi2.jatrik.core.domain.eventos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;

@Entity
@DiscriminatorValue(value = "1")
public class EventoPartidoGol extends EventoPartido {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2667783613146078587L;

	public EventoPartidoGol() {
		super();
	}



	public EventoPartidoGol(Integer minuto, Partido partido, Evento evento, Jugador jugador, Equipo equipo) {
		super(minuto, partido, evento);
		this.jugador = jugador;
		this.equipo = equipo;
	}

	@OneToOne
	private Jugador jugador;

	@OneToOne
	private Equipo equipo;

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
	
	@Override
	public String toString(){
		return String.format(this.getEvento().getComentario(), jugador.getNombre(), equipo.getNombre(), this.getPartido().getLocal().getNombre(),
				this.getPartido().getGolesLocal(), this.getPartido().getGolesVisitante(), this.getPartido().getVisitante().getNombre());
	}

}
