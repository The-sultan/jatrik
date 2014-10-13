package uy.edu.fing.tsi2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import uy.edu.fing.tsi2.model.SessionBeanJatrik;

@Named
@RequestScoped
public class EntrenamientoController {
	
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
	
	
	
	public EntrenamientoController() {
		
	}
	
	

}
