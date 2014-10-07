package uy.edu.fing.tsi2.front.ejb.implementations;


import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.ejb.Stateless;

import uy.edu.fing.tsi2.front.ejb.interfaces.UsuarioEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.exceptions.RestClientException;
import uy.edu.fing.tsi2.jatrik.common.payloads.Usuario;

/**
 *
 * @author c753388
 */
@Stateless
public class UsuarioEJB implements UsuarioEJBLocal {

	@EJB
	private RestClientEJBLocal jatrikCoreClient;
	
    private static final Logger logger = Logger.getLogger(UsuarioEJB.class.getName());
        
    public Long crearUsuario(String nombre,String mail,String nick,String password){
       try{ 
            Usuario usr = new Usuario();
            usr.setNick(nick);
            usr.setNombreEquipo("Team 1");
            usr.setEmail(mail);
            usr.setPassword(password);
			usr.setAlturaEstadio(1000);
			usr.setLatitudEstadio(1d);
			usr.setLongitudEstadio(2d);
			usr.setNombreUsuario(nombre);
			usr.setNombreEstadio("Estadio 1");
			Long usuarioId = Long.valueOf(jatrikCoreClient.postNuevoUsuario(usr));
			return usuarioId;
       }catch(RestClientException | NumberFormatException e){
            //logger.error(e.getMessage());
		   throw e;
            
       }
    }
    
}
