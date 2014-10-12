package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumHabilidad;


@Entity
@Table(name="HABILIDADES")
public class Habilidad implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2277825073738746960L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="VALOR")
	private int valor;
	
	@Column(name="TIPO")
	private EnumHabilidad tipo;

	@Column(name="ULTIMO_ENTRENAMIENTO")
	private Date ultimoEntrenamiento;
	
	public Date getUltimoEntrenamiento() {
		return ultimoEntrenamiento;
	}

	public void setUltimoEntrenamiento(Date ultimoEntrenamiento) {
		this.ultimoEntrenamiento = ultimoEntrenamiento;
	}


	public Habilidad() {
		super();
	}
	
   
	public Habilidad(int valor,EnumHabilidad tipo, String descripcion){
		this.valor = valor;
		this.tipo = tipo;
		this.descripcion = descripcion;
		Date date = new Date();
		this.ultimoEntrenamiento = date;
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


	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}


	public EnumHabilidad getTipo() {
		return tipo;
	}


	public void setTipo(EnumHabilidad tipo) {
		this.tipo = tipo;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Habilidad)) {
			return false;
		}
		Habilidad other = (Habilidad) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.Habilidad[ id=" + id + " ]";
	}
}
