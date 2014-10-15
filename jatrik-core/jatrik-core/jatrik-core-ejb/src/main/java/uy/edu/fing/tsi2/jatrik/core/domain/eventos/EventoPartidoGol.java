package uy.edu.fing.tsi2.jatrik.core.domain.eventos;

import java.text.MessageFormat;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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



	public EventoPartidoGol(Integer minuto, Partido partido, Evento evento, Jugador jugador, Equipo equipo, int golLocal, int golVisitante) {
		super(minuto, partido, evento);
		this.jugador = jugador;
		this.equipo = equipo;
		this.golLocal = Long.valueOf(golLocal);
		this.golVisitante = Long.valueOf(golVisitante);
	}

	@OneToOne
	@JoinColumn(name = "JUGADOR_ID", referencedColumnName = "ID")
	private Jugador jugador;

	@OneToOne
	@JoinColumn(name = "EQUIPO_ID", referencedColumnName = "ID")
	private Equipo equipo;

	@Column
	private Long golLocal;
	
	@Column
	private Long golVisitante;
	
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

	public Long getGolLocal() {
		return golLocal;
	}

	public void setGolLocal(Long golLocal) {
		this.golLocal = golLocal;
	}

	public Long getGolVisitante() {
		return golVisitante;
	}

	public void setGolVisitante(Long golVisitante) {
		this.golVisitante = golVisitante;
	}
	
	
	
	@Override
	public String toString(){
		return MessageFormat.format(this.getEvento().getComentario(), jugador.getNombre(), equipo.getNombre(), this.getPartido().getLocal().getNombre(),
				this.getGolLocal(), this.getGolVisitante(), this.getPartido().getVisitante().getNombre());
	}

}
