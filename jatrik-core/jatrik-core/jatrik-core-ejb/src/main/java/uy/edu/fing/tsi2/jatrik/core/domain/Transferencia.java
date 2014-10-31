package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@NamedQueries({ 
	@NamedQuery(name = "findTransferenciasdelEquipo", query = "SELECT OBJECT(u) FROM Transferencia u WHERE u.vendedor.id = :idEquipo "),
	@NamedQuery(name = "findTransferenciasLibres", query = "SELECT OBJECT(u) FROM Transferencia u WHERE u.comprador is null")

})
@Entity
@Table(name = "TRANSFERENCIAS")
public class Transferencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4876606747881701171L;

	@Id
	@SequenceGenerator(name="SEQ_TRANSFERENCIAS",sequenceName="SEQ_TRANSFERENCIAS",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TRANSFERENCIAS")
	@Column(name="ID")
	private Long id;
	
	
	@OneToOne(targetEntity = Jugador.class)
	@JoinColumn(name="JUGADOR_ID", referencedColumnName="ID")
	private Jugador jugador;
	
	@OneToOne(targetEntity = Equipo.class)
	@JoinColumn(name = "EQUIPO_VENDEDOR_ID", referencedColumnName = "ID")
	private Equipo vendedor;
	
	@Column(name = "PRECIO")
	private Double precio;

	@OneToOne(targetEntity = Equipo.class)
	@JoinColumn(name = "EQUIPO_COMPRADOR_ID", referencedColumnName = "ID")
	private Equipo comprador;

	
	public Transferencia() {
		super();
	}

	
	public Transferencia(Jugador jugador, Equipo vendedor, Double precio,
			Equipo comprador) {
		super();
		this.jugador = jugador;
		this.vendedor = vendedor;
		this.precio = precio;
		this.comprador = comprador;
	}

	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Jugador getJugador() {
		return jugador;
	}


	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}


	public Equipo getVendedor() {
		return vendedor;
	}


	public void setVendedor(Equipo vendedor) {
		this.vendedor = vendedor;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	public Equipo getComprador() {
		return comprador;
	}


	public void setComprador(Equipo comprador) {
		this.comprador = comprador;
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
		if (!(object instanceof Transferencia)) {
			return false;
		}
		Transferencia other = (Transferencia) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.Transferencia[ id=" + id + " ]";
	}

}
