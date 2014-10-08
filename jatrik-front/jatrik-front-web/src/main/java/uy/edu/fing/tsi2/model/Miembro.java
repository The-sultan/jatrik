package uy.edu.fing.tsi2.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("serial")
@XmlRootElement
public class Miembro implements Serializable {

	private Long id;

	
	@Size(min = 1, max = 25, message="Debe ingresar un nombre")
	@Pattern(regexp = "[^0-9]*", message = "No debe contener numeros")
	private String nombre;

	
	@Size(min = 1, max = 25, message="Debe ingresar un apellido")
	@Pattern(regexp = "[^0-9]*", message = "No debe contener numeros")
	private String apellido;

	@Size(min = 1, max = 15, message="Debe ingresar un nick")
	private String nick;
	
	private String password;
	
	@Size(min = 1, max = 25, message="Debe ingresar un e-mail")
	@Pattern(regexp= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Formato de e-mail incorrecto")
	private String email;

	@NotNull(message="Debe seleccionar un pais")
	private Integer pais;

	
	@Size( min = 1, max = 25, message="Debe ingresar un nombre para su equipo")
	@Pattern(regexp = "[^0-9]*", message = "No debe contener numeros")
	private String nombreEquipo;
	
	@Size( min = 1, max = 25, message="Debe ingresar un nombre para su estadio")
	@Pattern(regexp = "[^0-9]*", message = "No debe contener numeros")
	private String nombreEstadio;

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getPais() {
		return pais;
	}

	public void setPais(Integer pais) {
		this.pais = pais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getNombreEstadio() {
		return nombreEstadio;
	}

	public void setNombreEstadio(String nombreEstadio) {
		this.nombreEstadio = nombreEstadio;
	}
}
