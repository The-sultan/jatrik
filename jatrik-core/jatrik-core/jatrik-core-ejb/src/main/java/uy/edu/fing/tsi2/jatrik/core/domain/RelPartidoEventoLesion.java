package uy.edu.fing.tsi2.jatrik.core.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "REL_PARTIDOS_EVENTOS_LESION")
@DiscriminatorValue(value = "1")
@PrimaryKeyJoinColumn(name = "ID")
public class RelPartidoEventoLesion extends RelPartidoEvento {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2667783613146078587L;



	public RelPartidoEventoLesion(Integer minuto, Partido partido, Evento evento, int gravedad, Jugador jugador, Equipo equipo) {
		super(minuto, partido, evento);
		this.gravedad = gravedad;
		this.jugador = jugador;
		this.jugadorId = jugador.getId();
		this.equipoId = equipo.getId();
	}

	@Column(name = "GRAVEDAD")
	private int gravedad;

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

	
	
	public int getGravedad() {
		return gravedad;
	}

	public void setGravedad(int gravedad) {
		this.gravedad = gravedad;
	}
	
	public RelPartidoEventoLesion(){
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
