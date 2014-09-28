package uy.edu.fing.tsi2.front.ejb.impl;


import java.util.logging.Logger;


import javax.ejb.Stateless;



import uy.edu.fing.tsi2.front.ejb.UsuarioFrontEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.Usuario;

/**
 *
 * @author c753388
 */
@Stateless
public class UsuarioFrontEJB implements UsuarioFrontEJBLocal {

    private static final Logger logger = Logger.getLogger(UsuarioFrontEJB.class.getName());
        
    public void crearUsuario(String nombre,String mail,String nick,String password){
       try{ 
            Usuario usr = new Usuario();
            usr.setNick(nick);
            usr.setNombre(nombre);
            usr.setEmail(mail);
            usr.setPassword(password);
       }catch(Exception e){
            //logger.error(e.getMessage());
            
       }
    }
    
}
