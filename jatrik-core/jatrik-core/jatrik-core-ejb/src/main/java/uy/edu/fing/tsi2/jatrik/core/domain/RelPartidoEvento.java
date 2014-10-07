package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "REL_PARTIDOS_EVENTOS")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="EVENTO_ID", discriminatorType=DiscriminatorType.INTEGER)
public class RelPartidoEvento implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8136531417478925881L;
	
	@Id
	@SequenceGenerator(name="SEQ_REL_PARTIDO_EVENTO",sequenceName="SEQ_REL_PARTIDO_EVENTO",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_REL_PARTIDO_EVENTO")
	@Column(name="ID")
	private Long id;  

	@Column(name = "PARTIDO_ID")
	private Long partidoId;

	@Column(name = "EVENTO_ID")
	private Long eventoId;

	@Column(name = "MINUTO")
	private Integer minuto;

	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

	@ManyToOne(targetEntity = Partido.class)
	@JoinColumn(name = "PARTIDO_ID", insertable = false, updatable = false)
	private Partido partido;

	@ManyToOne(targetEntity = Evento.class)
	@JoinColumn(name = "EVENTO_ID", insertable = false, updatable = false)
	private Evento evento;

	public RelPartidoEvento() {
		super();
	}

	public RelPartidoEvento(Integer minuto, Partido partido, Evento evento) {
		this.minuto = minuto;
		this.partido = partido;
		this.evento = evento;

		// Valores para los identificadores
		this.partidoId = partido.getId();
		this.eventoId = evento.getId();
	}

	
	public Partido getPartido() {
		return partido;
	}


	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Evento getEvento() {
		return evento;
	}

	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}


	public Long getPartidoId() {
		return partidoId;
	}

	public void setPartidoId(Long partidoId) {
		this.partidoId = partidoId;
	}

	public Long getEventoId() {
		return eventoId;
	}

	public void setEventoId(Long eventoId) {
		this.eventoId = eventoId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof RelPartidoEvento)) {
			return false;
		}
		RelPartidoEvento other = (RelPartidoEvento) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.RelPartidoEvento[ id=" + id + " ]";
	}

}
