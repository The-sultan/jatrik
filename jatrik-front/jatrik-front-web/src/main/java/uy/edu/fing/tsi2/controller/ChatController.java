package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import uy.edu.fing.tsi2.front.ejb.interfaces.UsuarioEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;



@SuppressWarnings("serial")
@Model
@RequestScoped
public class ChatController implements Serializable {

	@Inject
	SessionBeanJatrik sessionBean;
	
	@EJB
	UsuarioEJBLocal usuariosEJB;
	
	@PostConstruct
	public void initDatos() {

	}

	public List<InfoUsuario> getUsuarios(){
		return usuariosEJB.getUsuarios();
	}
	
}
