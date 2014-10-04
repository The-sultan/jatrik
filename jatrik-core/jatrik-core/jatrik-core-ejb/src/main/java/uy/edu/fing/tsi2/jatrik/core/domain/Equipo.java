package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@NamedQueries({
	@NamedQuery(name="findEquipoLibre",query="SELECT OBJECT(u) FROM Equipo u WHERE u.usuario is null ")
	
})
@Entity
@Table(name="EQUIPOS")
public class Equipo implements Serializable {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7478814344336185050L;

	@Id
	@SequenceGenerator(name="SEQ_EQUIPOS",sequenceName="SEQ_EQUIPOS",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_EQUIPOS")
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOMBRE")
	private String Nombre;
	
	@Column(name="FONDOS")
	private Double fondos=0.0;
	
	@Column(name="ALTURA")
	private int altura;
	
	@Column(name="LATITUD")
	private double latitud;
	
	@Column(name="LONGITUD")
	private double longitud;
	
	@Column(name="ESTADIO")
	private String estadio;
	
	@OneToOne(targetEntity = Usuario.class)
	@JoinColumn(name="USUARIO_ID", referencedColumnName="ID")
	private Usuario usuario;
	
	@OneToMany(targetEntity = Jugador.class, mappedBy = "equipo", fetch=FetchType.EAGER)
	private Set<Jugador> jugadores = new HashSet<Jugador>();
	
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



	public int getAltura() {
		return altura;
	}



	public void setAltura(int altura) {
		this.altura = altura;
	}



	public double getLatitud() {
		return latitud;
	}



	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}



	public double getLongitud() {
		return longitud;
	}



	public void setLongitud(double longitud) {
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



	public Set<Jugador> getJugadores() {
		return jugadores;
	}



	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
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
