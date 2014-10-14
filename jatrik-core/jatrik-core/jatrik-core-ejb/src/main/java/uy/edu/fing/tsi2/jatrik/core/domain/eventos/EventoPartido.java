package uy.edu.fing.tsi2.jatrik.core.domain.eventos;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;

@NamedQueries({ @NamedQuery(name = "findEventosPartidoByPartido", query = "SELECT OBJECT(e) FROM EventoPartido e WHERE e.partido = :partido ")
})

@Entity
@Table(name = "EVENTOS_PARTIDO")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO_EVENTO", discriminatorType=DiscriminatorType.INTEGER)
public class EventoPartido implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8136531417478925881L;
	
	@Id
	@SequenceGenerator(name="SEQ_REL_PARTIDO_EVENTO",sequenceName="SEQ_REL_PARTIDO_EVENTO",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_REL_PARTIDO_EVENTO")
	@Column(name="ID")
	private Long id;  

	@Column(name = "MINUTO")
	private Integer minuto;

	@ManyToOne()
	@JoinColumn(name = "PARTIDO_ID", referencedColumnName = "ID")
	private Partido partido;

	@ManyToOne(targetEntity = Evento.class)
	@JoinColumn(name = "EVENTO_ID",referencedColumnName = "ID")
	private Evento evento;

	public EventoPartido() {
		super();
	}

	public EventoPartido(Integer minuto, Partido partido, Evento evento) {
		this.minuto = minuto;
		this.partido = partido;
		this.evento = evento;
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


	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
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
		if (!(object instanceof EventoPartido)) {
			return false;
		}
		EventoPartido other = (EventoPartido) object;
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
