package uy.edu.fing.tsi2.jatrik.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@NamedQueries({
	@NamedQuery(name="findUsuarioByNick",query="SELECT OBJECT(u) FROM Usuario u WHERE u.nick = :nick "),
	@NamedQuery(name="findUsuarioByNickP�ss",query="SELECT OBJECT(u) FROM Usuario u WHERE u.nick = :nick and u.password= :pass")
})
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "nick"))
public class Usuario implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 789185968854210197L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;    

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String nick;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String email;

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


