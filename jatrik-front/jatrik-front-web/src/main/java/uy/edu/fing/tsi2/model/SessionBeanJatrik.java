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
	
	public SessionBeanJatrik() {
		
	}

	public InfoUsuario getInfo() {
		return infoUsuario;
	}

	public void setInfo(InfoUsuario infoUsuario) {
		this.infoUsuario = infoUsuario;
	}


}
