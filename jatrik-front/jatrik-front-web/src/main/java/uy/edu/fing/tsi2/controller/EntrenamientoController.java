package uy.edu.fing.tsi2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;

@Model
@RequestScoped
public class EntrenamientoController {
 
	@Inject
	SessionBeanJatrik sessionBean;
	
	@EJB
	EquipoEJBLocal equipoEJB;
	
	private List<String> images;
	
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        
        images.add("resources/gfx/Entrenamientos/ataque.png");
        images.add("resources/gfx/Entrenamientos/defensa.png");
        images.add("resources/gfx/Entrenamientos/mediocampo.png");
        
    }
 
    public List<String> getImages() {
        return images;
    }
	
    public String entrenar(Long modo) {
		FacesMessage msg = null;
    	//Aca esta el servicio para entrenar
    	String Respuesta = equipoEJB.entrenarEquipo(sessionBean.getInfoUsuario().getInfoEquipo().getId(), Integer.valueOf(modo.toString()));
    	
    	if(Respuesta.contains("Ya"))
    		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Respuesta);
    	else
    		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", Respuesta);
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    	return null;
    }	
	
	public void setImages(List<String> images) {
		this.images = images;
	}

	public EntrenamientoController() {
		
	}
	
	

}
