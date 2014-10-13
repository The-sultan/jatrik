package uy.edu.fing.tsi2.jatrik.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="COMENTARIOS")
public class Comentario {

	@Id
	@SequenceGenerator(name="SEQ_COMENTARIOS",sequenceName="SEQ_COMENTARIOS",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_COMENTARIOS")
	@Column(name="ID")
	private Long id;
	
	@Column
	private String descripcion;
	
	@Column
	private Long espectacularidad;

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

	public Long getEspectacularidad() {
		return espectacularidad;
	}

	public void setEspectacularidad(Long espectacularidad) {
		this.espectacularidad = espectacularidad;
	}
	
	
}
