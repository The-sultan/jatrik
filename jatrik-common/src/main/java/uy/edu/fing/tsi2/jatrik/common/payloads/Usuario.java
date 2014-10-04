package uy.edu.fing.tsi2.jatrik.common.payloads;


/**
 * @author Farid
 */
public class Usuario {

	private Long id;    

	private String nombreUsuario;

	private String nick;

	private String password;

	private String email;
	
	private int alturaEstadio;
	
	private double latitudEstadio;
	
	private double longitudEstadio;
	
	private String nombreEstadio;
	
	private String nombreEquipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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

	public int getAlturaEstadio() {
		return alturaEstadio;
	}

	public void setAlturaEstadio(int alturaEstadio) {
		this.alturaEstadio = alturaEstadio;
	}

	public double getLatitudEstadio() {
		return latitudEstadio;
	}

	public void setLatitudEstadio(double latitudEstadio) {
		this.latitudEstadio = latitudEstadio;
	}

	public double getLongitudEstadio() {
		return longitudEstadio;
	}

	public void setLongitudEstadio(double longitudEstadio) {
		this.longitudEstadio = longitudEstadio;
	}

	public String getNombreEstadio() {
		return nombreEstadio;
	}

	public void setNombreEstadio(String nombreEstadio) {
		this.nombreEstadio = nombreEstadio;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	
	
}
