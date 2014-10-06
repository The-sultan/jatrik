package uy.edu.fing.tsi2.front.beans;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import uy.edu.fing.tsi2.front.ejb.interfaces.UsuarioEJBLocal;





/**
 *
 * @author c753388
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6970101878418966024L;
   
	private Logger logger = Logger.getLogger(UsuarioBean.class.getName());

	@EJB
    UsuarioEJBLocal AdminUsuarios;
    
    private String nick;
    private String password;
    private String email;
    private String nombre;
    
    public UsuarioBean() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void validateEmail(FacesContext context, UIComponent validated,Object value) {
    	 String correo = (String) value;
         if( ! correo.toLowerCase().matches( "^[a-z0-9]+([_\\.-][a-z0-9]+)*@([a-z0-9]+([\\.-][a-z0-9]+)*)+\\.[a-z]{2,}" ) ) {
            FacesMessage message = new FacesMessage("La direcci�n de email, no es correcta");
            throw new ValidatorException(message);
         }
	}
    
    /*
	public void validateUsuario(FacesContext context, UIComponent validated,Object value) {
    	String nick = (String) value;
    	 if (!AdminUsuarios.findUsuarioByUser(nick)){
    		 FacesMessage message = new FacesMessage("El nick ingresado esta duplicado");
             throw new ValidatorException(message);
    	 }
    }
    */
    /**
     * @param context
     * @param validated
     * @param value
     */			/*
    public void validatePassword(FacesContext context, UIComponent validated,Object value) {
    	
    	String confirm= (String) value;
    	UIInput passComp = (UIInput) validated.getAttributes().get("passwordComponent");     
        String password=(String)passComp.getValue();     
    	
        if (!password.equals(confirm)) {     
            FacesMessage message = new FacesMessage("Password y Confirm Password Should deben ser iguales");
            throw new ValidatorException(message);
        }
    }
    */
    
	
	public String altaUsuario(){
        String outcome = null;
        System.out.println("ALTA USUARIO");
        
        AdminUsuarios.crearUsuario(this.nombre, this.email, this.nick, this.password);
        return outcome;
    }
    /*
    public String validarUsuario(){
        String outcome = null;
        if (AdminUsuarios.findUsuarioByNickPassword(this.nick,this.password)){
            outcome = "validado";
        }else{
            outcome="error";
        }
        return outcome;
    }*/
}
