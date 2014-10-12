package uy.edu.fing.tsi2.jatrik.core.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "REL_PARTIDOS_EVENTOS_TARJETA_AMARILLA")
@DiscriminatorValue(value = "2")
@PrimaryKeyJoinColumn(name = "ID")
public class RelPartidoEventoTarjetaAmarillla extends RelPartidoEvento {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7436170795069356479L;



	public RelPartidoEventoTarjetaAmarillla(Integer minuto, Partido partido, Evento evento, Jugador jugador, Equipo equipo) {
		super(minuto, partido, evento);
		this.jugador = jugador;
		this.jugadorId = jugador.getId();
		this.equipoId = equipo.getId();
	}

	@Column(name = "JUGADOR_ID")
	private Long jugadorId;

	@Column(name = "EQUIPO_ID")
	private Long equipoId;

	
	@ManyToOne(targetEntity = Jugador.class)
	@JoinColumn(name = "JUGADOR_ID", insertable = false, updatable = false)
	private Jugador jugador;


	@ManyToOne(targetEntity = Equipo.class)
	@JoinColumn(name = "EQUIPO_ID", insertable = false, updatable = false)
	private Equipo equipo;

	
	
	public RelPartidoEventoTarjetaAmarillla(){
		super();
	}

	public Long getJugadorId() {
		return jugadorId;
	}

	public void setJugadorId(Long jugadorId) {
		this.jugadorId = jugadorId;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Long getEquipoId() {
		return equipoId;
	}

	public void setEquipoId(Long equipoId) {
		this.equipoId = equipoId;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

}
