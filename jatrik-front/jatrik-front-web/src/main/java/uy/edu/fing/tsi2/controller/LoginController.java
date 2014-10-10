package uy.edu.fing.tsi2.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import uy.edu.fing.tsi2.model.LoginDatos;

import java.io.Serializable;

@SuppressWarnings("serial")
@Model
@SessionScoped
public class LoginController implements Serializable {

	@Named
	@Produces
	private LoginDatos datos;

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
		if (datos.getNick() != null && datos.getNick().equals("admin") && datos.getPassword() != null
				&& datos.getPassword().equals("admin")) {
			datos.setLogueado(true); 
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@",
					datos.getNick());
		} else {
			datos.setLogueado(false); 
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
					"Credenciales no válidas");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", datos.isLogueado());
		if (datos.isLogueado())
			context.addCallbackParam("view", "equipo.xhtml");
	}

	public LoginDatos getDatos() {
		return datos;
	}

	public void setDatos(LoginDatos datos) {
		this.datos = datos;
	}

}
