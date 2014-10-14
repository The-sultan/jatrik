package uy.edu.fing.tsi2.jatrik.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="findComnetarioByEventoAndNivel",query="SELECT OBJECT(c) FROM Comentario c WHERE c.evento = :evento AND c.nivel = :nivel")
})

@Entity
@Table(name="COMENTARIOS")
public class Comentario {

	@Id
	@SequenceGenerator(name="SEQ_COMENTARIOS",sequenceName="SEQ_COMENTARIOS",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_COMENTARIOS")
	@Column(name="ID")
	private Long id;
	
	@OneToOne
	private Evento evento;
	
	@Column
	private String descripcion;
	
	@Column
	private Long nivel;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getNivel() {
		return nivel;
	}

	public void setNivel(Long nivel) {
		this.nivel = nivel;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
}
