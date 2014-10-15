package uy.edu.fing.tsi2.jatrik.core.domain.eventos;

import java.text.MessageFormat;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;

@Entity
@DiscriminatorValue(value = "7")
public class EventoPartidoTarjetaAmarillaDoble extends EventoPartido {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7436170795069356479L;



	public EventoPartidoTarjetaAmarillaDoble(Integer minuto, Partido partido, Evento evento, Jugador jugador, Equipo equipo) {
		super(minuto, partido, evento);
		this.jugador = jugador;
		this.equipo = equipo;
	}

	@ManyToOne(targetEntity = Jugador.class)
	@JoinColumn(name = "JUGADOR_ID", referencedColumnName = "ID")
	private Jugador jugador;


	@ManyToOne(targetEntity = Equipo.class)
	@JoinColumn(name = "EQUIPO_ID", referencedColumnName = "ID")
	private Equipo equipo;

	
	
	public EventoPartidoTarjetaAmarillaDoble(){
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
	
	@Override
	public String toString(){
		return MessageFormat.format(this.getEvento().getComentario(), this.getJugador().getNombre(), this.getEquipo().getNombre());
	}

}
