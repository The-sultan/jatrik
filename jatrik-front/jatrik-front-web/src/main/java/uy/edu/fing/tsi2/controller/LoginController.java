package uy.edu.fing.tsi2.controller;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import uy.edu.fing.tsi2.front.ejb.interfaces.UsuarioEJBLocal;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;


@Model
public class LoginController  {

	@Inject
	SessionBeanJatrik sessionBean;
	
	@EJB
	private UsuarioEJBLocal usuarioEJB;

	@PostConstruct
	public void initDatos() {
	}

	public String login() throws Exception {

		return "";
	}

	public void loginDelay(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		if (sessionBean.getNick() != null && sessionBean.getPassword() != null) {
			
			try {
				sessionBean.setInfoUsuario(usuarioEJB.login(sessionBean.getNick(), sessionBean.getPassword()));
				sessionBean.setLogueado(true);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@",
						sessionBean.getNick());
			} catch (RuntimeException e) {
				sessionBean.setLogueado(false);
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
						"Error en logueo");
			}
			
		} else {
			sessionBean.setLogueado(false);
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
					"Credenciales no válidas");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", sessionBean.isLogueado());

	}

	public void logout() {
		FacesMessage msg = null;
		if(sessionBean.isLogueado()){
			/*
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false);
			session.invalidate();*/
			sessionBean.setLogueado(false);
			sessionBean.setNick(null);
			sessionBean.setPassword(null);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Hasta luego ",
					sessionBean.getNick());
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No esta logueado","");
		}
				
		FacesContext.getCurrentInstance().addMessage(null, msg);
		 
	}

	public SessionBeanJatrik getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBeanJatrik sessionBean) {
		this.sessionBean = sessionBean;
	}

}
