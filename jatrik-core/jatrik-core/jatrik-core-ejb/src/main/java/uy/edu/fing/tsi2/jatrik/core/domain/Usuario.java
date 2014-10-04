package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@NamedQueries({
	@NamedQuery(name="findUsuarioByNick",query="SELECT OBJECT(u) FROM Usuario u WHERE u.nick = :nick ")
	
})
@Entity
@Table(name="USUARIOS",uniqueConstraints = @UniqueConstraint(columnNames = "nick"))
public class Usuario implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 789185968854210197L;

	@Id	
	@SequenceGenerator(name="SEQ_USUARIOS",sequenceName="SEQ_USUARIOS",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_USUARIOS")
   	@Column(name="ID")
	private Long id;    

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name = "NICK")
	private String nick;

	@Column(name="PASSWORD")
	private String password;

	@Column(name = "EMAIL")
	private String email;
	
	@OneToOne
	private Equipo equipo;
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
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
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "uy.com.fing.tsi2.entidades.Usuario[ id=" + id + " ]";
	}

}


