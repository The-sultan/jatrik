package uy.edu.fing.tsi2.jatrik.android.extras;

/**
 * @author Rafael
 */

public class InfoUsuario{

	private Long id;    

	private String nombre;

	private String nick;

	private String password;

	private String email;
	
	private InfoEquipo infoEquipo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public InfoEquipo getInfoEquipo() {
		return infoEquipo;
	}

	public void setInfoEquipo(InfoEquipo infoEquipo) {
		this.infoEquipo = infoEquipo;
	}



	
	
}
