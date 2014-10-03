package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuesto;

@Entity
@Table(name = "JUAGADORES")
public class Jugador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2640962344507593130L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "EDAD")
	private int edad;

	@Column(name = "ALTURA")
	private int altura;

	@Column(name = "PESO")
	private int peso;

	@Column(name = "PUESTO")
	@Enumerated(EnumType.ORDINAL)
	private EnumPuesto puesto;

	@Column(name = "EN_VENTA")
	private Boolean enVenta;

	@Column(name = "NRO_CAMISETA")
	private int nro_Camiseta;

	@ManyToOne(targetEntity = Equipo.class)
	@JoinColumn(name = "EQUIPO_ID", referencedColumnName = "ID")
	private Equipo equipo;
	
	@ManyToMany( fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "JUGADORES_HABILIDADES",  
			joinColumns = {@JoinColumn(name = "JUGADOR_ID")},
			inverseJoinColumns = {@JoinColumn(name = "HABILIDAD_ID")
			}
	)
	private List<Habilidad> habilidades = new LinkedList<Habilidad>(); 

	
	
	public Jugador() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public EnumPuesto getPuesto() {
		return puesto;
	}

	public void setPuesto(EnumPuesto puesto) {
		this.puesto = puesto;
	}

	public Boolean getEnVenta() {
		return enVenta;
	}

	public void setEnVenta(Boolean enVenta) {
		this.enVenta = enVenta;
	}

	public int getNro_Camiseta() {
		return nro_Camiseta;
	}

	public void setNro_Camiseta(int nro_Camiseta) {
		this.nro_Camiseta = nro_Camiseta;
	}

	
	
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public List<Habilidad> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidad> habilidades) {
		this.habilidades = habilidades;
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
		if (!(object instanceof Jugador)) {
			return false;
		}
		Jugador other = (Jugador) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.Jugador[ id=" + id + " ]";
	}

}
