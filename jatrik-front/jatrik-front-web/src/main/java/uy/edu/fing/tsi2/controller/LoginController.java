package uy.edu.fing.tsi2.controller;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
import uy.edu.fing.tsi2.model.LoginDatos;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;


@Model
public class LoginController  {

	@Named
	@Produces
	private LoginDatos datos;


	@Inject
	SessionBeanJatrik sessionBean;
	
	@EJB
	private UsuarioEJBLocal usuarioEJB;

	@PostConstruct
	public void initDatos() {

		datos = new LoginDatos();

	}

	public String login() throws Exception {

		return "";
	}

	public void loginDelay(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		if (datos.getNick() != null && datos.getPassword() != null) {
			
			try {
				sessionBean.setInfo( usuarioEJB.login(datos.getNick(), datos.getPassword()));
				datos.setLogueado(true);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@",
						datos.getNick());
			} catch (RuntimeException e) {
				datos.setLogueado(false);
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
						"Error en logueo");
			}
			
		} else {
			datos.setLogueado(false);
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
					"Credenciales no válidas");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", datos.isLogueado());

	}

	public void logout() {
		FacesMessage msg = null;
		if(datos.isLogueado()){
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false);
			session.invalidate();
			datos = new LoginDatos();
			
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Hasta luego ",
					datos.getNick());
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No esta logueado","");
		}
				
		FacesContext.getCurrentInstance().addMessage(null, msg);
		 
	}

	public LoginDatos getDatos() {
		return datos;
	}

	public void setDatos(LoginDatos datos) {
		this.datos = datos;
	}



}
