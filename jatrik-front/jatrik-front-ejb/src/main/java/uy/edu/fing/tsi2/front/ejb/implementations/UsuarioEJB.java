package uy.edu.fing.tsi2.front.ejb.implementations;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.front.ejb.interfaces.UsuarioEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.exceptions.RestClientException;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEstadio;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;

/**
 *
 * @author c753388
 */
@Stateless
public class UsuarioEJB implements UsuarioEJBLocal {

	@EJB
	private RestClientEJBLocal jatrikCoreClient;
	
    public Long crearUsuario(String nombre,String mail,String nick,String password, String nombreEquipo, int idPais){
        try{ 
             InfoUsuario usr = new InfoUsuario();
             usr.setNombre(nombre);
             usr.setNick(nick);
             usr.setEmail(mail);
             usr.setPassword(password);
             InfoEquipo infoEquipo = new InfoEquipo();
             infoEquipo.setNombre(nombreEquipo);
             InfoEstadio infoEstadio = new InfoEstadio();
             
             //hardcoded properties------//
             infoEstadio.setAltura(1000);
             infoEstadio.setLatitud(-54);
             infoEstadio.setLongitud(60);
             //--------------------------//
 			
             usr.setInfoEquipo(infoEquipo);
             infoEquipo.setEstadio(infoEstadio);
 			
 			Long usuarioId = Long.valueOf(jatrikCoreClient.postNuevoUsuario(usr));
 			return usuarioId;
        }catch(RestClientException | NumberFormatException e){
 		   throw e;
             
        }
     }

	@Override
	public InfoUsuario login(String nick, String pass) {
		return jatrikCoreClient.validarYObtenerUsuario(nick, pass);
	}
    
}
