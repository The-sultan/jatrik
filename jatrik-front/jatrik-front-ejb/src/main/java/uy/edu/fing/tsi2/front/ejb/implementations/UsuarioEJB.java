package uy.edu.fing.tsi2.front.ejb.implementations;


import java.util.List;

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
	
    public Long crearUsuario(String nombre,String mail,String nick,String password, String nombreEquipo, String nombreEstadio, int idPais, String latitudEstadio, String longitudEstadio){
        try{ 
             InfoUsuario usr = new InfoUsuario();
             usr.setNombre(nombre);
             usr.setNick(nick);
             usr.setEmail(mail);
             usr.setPassword(password);
             InfoEquipo infoEquipo = new InfoEquipo();
             infoEquipo.setNombre(nombreEquipo);
             InfoEstadio infoEstadio = new InfoEstadio();
             
             infoEstadio.setNombre(nombreEstadio);
             //hardcoded properties------//
             infoEstadio.setAltura(1000);
             infoEstadio.setLatitud(Double.parseDouble(latitudEstadio));
             infoEstadio.setLongitud(Double.parseDouble(longitudEstadio));
             //--------------------------//
 			
             infoEquipo.setEstadio(infoEstadio);
             usr.setInfoEquipo(infoEquipo);
 			
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
    
	@Override
	public List<InfoUsuario> getUsuarios(){
		return jatrikCoreClient.getUsuarios();
	}
}
