package uy.edu.fing.tsi2.model;


import java.io.Serializable;


import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class SessionBeanJatrik implements Serializable {

	
	private InfoUsuario infoUsuario;
	private String nick;
	private String password;
	private boolean logueado;
	
	public SessionBeanJatrik() {
		
	}

	public boolean isLogueado() {
		return logueado;
	}

	public void setLogueado(boolean logueado) {
		this.logueado = logueado;
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

	public InfoUsuario getInfoUsuario() {
		return infoUsuario;
	}

	public void setInfoUsuario(InfoUsuario infoUsuario) {
		this.infoUsuario = infoUsuario;
	}

	

}
