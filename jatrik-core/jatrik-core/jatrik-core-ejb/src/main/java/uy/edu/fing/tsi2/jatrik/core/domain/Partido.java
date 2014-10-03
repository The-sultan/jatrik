package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEstadoPartido;


@Entity
@Table(name="PARTIDOS")
public class Partido implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6815382124183394403L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;    

	@Column(name="FECHA_INICIO")
	private Calendar fechaInicio;
	
	@Column(name="FECHA_FIN")
	private Calendar fechaFin;
	
	@Column(name = "ESTADO")
	@Enumerated(EnumType.ORDINAL)
	private EnumEstadoPartido estado;
	
	@Column(name = "GOLES_LOCAL")
	private int golesLocal;

	@Column(name = "GOLES_VISITANTE")
	private int golesVisitante;

	@ManyToOne(targetEntity = Equipo.class)
	@JoinColumn(name = "LOCAL_ID", referencedColumnName = "ID")
	private Equipo local;

		
	@ManyToOne(targetEntity = Equipo.class)
	@JoinColumn(name = "VISITANTE_ID", referencedColumnName = "ID")
	private Equipo visitante;

	
	public Partido() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Calendar getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Calendar getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}


	public EnumEstadoPartido getEstado() {
		return estado;
	}


	public void setEstado(EnumEstadoPartido estado) {
		this.estado = estado;
	}


	public int getGolesLocal() {
		return golesLocal;
	}


	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}


	public int getGolesVisitante() {
		return golesVisitante;
	}


	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
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
		if (!(object instanceof Partido)) {
			return false;
		}
		Partido other = (Partido) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.Partido[ id=" + id + " ]";
	}
	
	
}
