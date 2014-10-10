package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


//import org.hibernate.annotations.CollectionOfElements;
//import org.hibernate.annotations.MapKey;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEstadoPartido;


@NamedQueries({
	@NamedQuery(name="findPartidosDeEquipo",query="SELECT OBJECT(p) FROM Partido p WHERE ((p.local.id = :idEquipo) OR (p.visitante.id = :idEquipo)) AND (p.estado = :estado)")
	
})
//@SuppressWarnings("deprecation")
@Entity
@Table(name="PARTIDOS")
public class Partido implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6815382124183394403L;

	
	@Id
	@SequenceGenerator(name="SEQ_PARTIDOS",sequenceName="SEQ_PARTIDOS",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_PARTIDOS")
	@Column(name="ID")
	private Long id;    

	@Column(name="FECHA_INICIO")
	private Date fechaInicio;
	

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

	@OneToOne(targetEntity = Formacion.class, cascade = CascadeType.ALL)
	@JoinColumn(name="FORMACION_LOCAL_ID", referencedColumnName="ID")
	private Formacion formacionLocal;

	@OneToOne(targetEntity = Formacion.class, cascade = CascadeType.ALL)
	@JoinColumn(name="FORMACION_VISITANTE_ID", referencedColumnName="ID")
	private Formacion formacionVisitante;	
	
	public Partido() {
		super();
	}


	public Partido(Date fecha, EnumEstadoPartido estado, Equipo local,
			Equipo visitante, int golesLocal, int golesVisitante) {
		
		super();
		this.fechaInicio = fecha;
		this.estado = estado;
		this.local = local;
		this.visitante = visitante;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
		//this.relPartidoEventos = new HashSet<RelPartidoEvento>();
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
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
   
	
	
	public Equipo getLocal() {
		return local;
	}


	public void setLocal(Equipo local) {
		this.local = local;
	}


	public Equipo getVisitante() {
		return visitante;
	}


	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
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
