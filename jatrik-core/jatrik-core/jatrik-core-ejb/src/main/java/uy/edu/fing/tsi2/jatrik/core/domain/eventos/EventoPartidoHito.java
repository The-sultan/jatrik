package uy.edu.fing.tsi2.jatrik.core.domain.eventos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;

@Entity
@DiscriminatorValue(value = "6")
public class EventoPartidoHito extends EventoPartido {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2667783613146078587L;

	public EventoPartidoHito() {
		super();
	}



	public EventoPartidoHito(Integer minuto, Partido partido, Evento evento) {
		super(minuto, partido, evento);
	}
	
	@Override
	public String toString(){
		return this.getEvento().getComentario();
	}

}
