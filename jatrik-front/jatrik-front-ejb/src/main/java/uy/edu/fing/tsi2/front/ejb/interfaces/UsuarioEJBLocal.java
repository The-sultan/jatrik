package uy.edu.fing.tsi2.front.ejb.interfaces;

import javax.ejb.Local;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;

/**
 *
 * @author c753388
 */
@Local
public interface UsuarioEJBLocal {
	
	 public Long crearUsuario(String nombre,String mail,String nick,String password, String nombreEquipo, int idPais);
	 
	 public InfoUsuario login(String nick,String pass);
    
}
