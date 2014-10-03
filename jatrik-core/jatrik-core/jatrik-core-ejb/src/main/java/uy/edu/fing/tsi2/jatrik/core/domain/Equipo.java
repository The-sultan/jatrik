package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="EQUIPOS")
public class Equipo implements Serializable {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7478814344336185050L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOMBRE")
	private String Nombre;
	
	@Column(name="FONDOS")
	private Double fondos=0.0;
	
	@Column(name="ALTURA")
	private Integer altura;
	
	@Column(name="LATITUD")
	private Integer latitud;
	
	@Column(name="LONGITUD")
	private Integer longitud;
	
	@Column(name="ESTADIO")
	private String estadio;
	
	@ManyToOne(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	@JoinColumn(name="USUARIO_ID", referencedColumnName="ID")
	private Usuario usuario;
	
	public Equipo() {
		super();
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return Nombre;
	}



	public void setNombre(String nombre) {
		Nombre = nombre;
	}



	public Double getFondos() {
		return fondos;
	}



	public void setFondos(Double fondos) {
		this.fondos = fondos;
	}



	public Integer getAltura() {
		return altura;
	}



	public void setAltura(Integer altura) {
		this.altura = altura;
	}



	public Integer getLatitud() {
		return latitud;
	}



	public void setLatitud(Integer latitud) {
		this.latitud = latitud;
	}



	public Integer getLongitud() {
		return longitud;
	}



	public void setLongitud(Integer longitud) {
		this.longitud = longitud;
	}



	public String getEstadio() {
		return estadio;
	}



	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}
   
	
	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		if (!(object instanceof Equipo)) {
			return false;
		}
		Equipo other = (Equipo) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.Equipo[ id=" + id + " ]";
	}
}
