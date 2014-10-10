package uy.edu.fing.tsi2.model;

import javax.inject.Named;

@Named
public class LoginDatos {

	private String nick;
	private String password;
	private boolean logueado;
	
	
	public LoginDatos() {
		
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



	public boolean isLogueado() {
		return logueado;
	}



	public void setLogueado(boolean logueado) {
		this.logueado = logueado;
	}

}
