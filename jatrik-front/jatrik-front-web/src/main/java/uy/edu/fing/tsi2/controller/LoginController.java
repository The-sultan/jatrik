package uy.edu.fing.tsi2.controller;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;


import uy.edu.fing.tsi2.front.ejb.interfaces.UsuarioEJBLocal;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;
import uy.edu.fing.tsi2.navigation.AjaxNavigator;


@Model
public class LoginController  {

	@Inject
	SessionBeanJatrik sessionBean;
	
	@EJB
	private UsuarioEJBLocal usuarioEJB;
	
	@Inject
	AjaxNavigator ajaxNav;

	@PostConstruct
	public void initDatos() {
	}

	public String login() throws Exception {

		return "";
	}

	public String loginDelay() {
		FacesMessage msg = null;
		if (sessionBean.getNick() != null && sessionBean.getPassword() != null) {
			
			try {
				sessionBean.setInfoUsuario(usuarioEJB.login(sessionBean.getNick(), sessionBean.getPassword()));
				sessionBean.setLogueado(true);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
				session.setAttribute("nick", sessionBean.getNick());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@",
						sessionBean.getNick());
				return "equipo";
			} catch (RuntimeException e) {
				sessionBean.setLogueado(false);
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
						"Error en logueo");
				return "index";
			}
			
		} else {
			sessionBean.setLogueado(false);
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
					"Credenciales no válidas");
			return "index";
		}
		

	}

	public String logout() {
		FacesMessage msg = null;
		if(sessionBean.isLogueado()){
			/*
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false);
			session.invalidate();*/
			sessionBean.setLogueado(false);
			sessionBean.setNick(null);
			sessionBean.setPassword(null);
			ajaxNav.controllerNavigate("home");
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Hasta luego ",
					sessionBean.getNick());
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No esta logueado","");
		}
				
		return "index";
		 
	}

	public SessionBeanJatrik getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBeanJatrik sessionBean) {
		this.sessionBean = sessionBean;
	}

}
