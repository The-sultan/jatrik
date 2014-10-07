package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "EJERCICIOS")
public class Ejercicio implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8437115830055627539L;
	
	@Id
	@SequenceGenerator(name = "SEQ_EJERCICIOS", sequenceName = "SEQ_EJERCICIOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EJERCICIOS")
	@Column(name = "ID")
	private Long id;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="VELOCIDAD")
	private int factorVelocidad;
	
	@Column(name="TECNICA")
	private int factorTecnica;
	
	@Column(name="DEFENSA")
	private int factorDefensa;
	
	@Column(name="ATAQUE")
	private int factorAtaque;
	
	@Column(name="PORTERIA")
	private int factorPorteria;
	
	public Ejercicio(){
		super();
	}
	
	public Ejercicio(String descripcion, int factorVelocidad, int factorTecnica,
			int factorDefensa, int factorAtaque, int factorPorteria) {
		super();
		this.descripcion = descripcion;
		this.factorVelocidad = factorVelocidad;
		this.factorTecnica = factorTecnica;
		this.factorDefensa = factorDefensa;
		this.factorAtaque = factorAtaque;
		this.factorPorteria = factorPorteria;
	}

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

	public int getFactorVelocidad() {
		return factorVelocidad;
	}

	public void setFactorVelocidad(int factorVelocidad) {
		this.factorVelocidad = factorVelocidad;
	}

	public int getFactorTecnica() {
		return factorTecnica;
	}

	public void setFactorTecnica(int factorTecnica) {
		this.factorTecnica = factorTecnica;
	}

	public int getFactorDefensa() {
		return factorDefensa;
	}

	public void setFactorDefensa(int factorDefensa) {
		this.factorDefensa = factorDefensa;
	}

	public int getFactorAtaque() {
		return factorAtaque;
	}

	public void setFactorAtaque(int factorAtaque) {
		this.factorAtaque = factorAtaque;
	}

	public int getFactorPorteria() {
		return factorPorteria;
	}

	public void setFactorPorteria(int factorPorteria) {
		this.factorPorteria = factorPorteria;
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
		if (!(object instanceof Ejercicio)) {
			return false;
		}
		Ejercicio other = (Ejercicio) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.Ejercicio[ id=" + id + " ]";
	}
		
}
