package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="findCorreosUsuario",query="SELECT OBJECT(u) FROM Correo u WHERE u.to.id = :id "),
        @NamedQuery(name="findCorreosUsuarioEnviados",query="SELECT OBJECT(u) FROM Correo u WHERE u.from.id = :id ")
	
})
@Entity
@Table(name = "CORREOS")
public class Correo implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8339236072660845864L;
	
	@Id
	@SequenceGenerator(name="SEQ_CORREOS",sequenceName="SEQ_CORREOS",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_CORREOS")
	@Column(name="ID")
	private Long id;

	@Column(name = "ASUNTO")
	private String asunto;

	@Column(name = "MENSAJE")
	private String mensaje;

	@Column(name = "LEIDO")
	private boolean leido;
	
	@Column(name = "FECHA")
	private Date fecha;

	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "FROM_ID" , referencedColumnName = "ID")
	private Usuario from;

	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "TO_ID", referencedColumnName = "ID")
	private Usuario to;

	public Correo() {
		super();

	}

	public Correo(String asunto, String mensaje, boolean leido, Date fecha, Usuario from, Usuario to) {
		super();
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.leido = leido;
		this.fecha = fecha;
		this.from = from;
		this.to = to;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean getLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getFrom() {
		return from;
	}

	public void setFrom(Usuario from) {
		this.from = from;
	}

	public Usuario getTo() {
		return to;
	}

	public void setTo(Usuario to) {
		this.to = to;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		
		if (!(object instanceof Correo)) {
			return false;
		}
		Correo other = (Correo) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.Correo[ id=" + id + " ]";
	}

}
