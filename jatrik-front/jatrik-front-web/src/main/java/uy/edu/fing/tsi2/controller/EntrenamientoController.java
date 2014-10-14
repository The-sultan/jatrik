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
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
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
	
    public void entrenar() {
		FacesMessage msg = null;
    	String Respuesta = "Funciona";
    	
    	//Aca esta el servicio para entrenar
    	//String Result = equipoEJB.entrenarEquipo(sessionBean.getInfo().getInfoEquipo().getId(), 1);
    	
    	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@",Respuesta);
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    }	
	
	public void setImages(List<String> images) {
		this.images = images;
	}

	public EntrenamientoController() {
		
	}
	
	

}
