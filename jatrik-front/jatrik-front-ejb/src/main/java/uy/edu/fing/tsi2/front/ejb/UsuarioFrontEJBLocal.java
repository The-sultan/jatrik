package uy.edu.fing.tsi2.front.ejb;

import javax.ejb.Local;

/**
 *
 * @author c753388
 */
@Local
public interface UsuarioFrontEJBLocal {
	
	 public void crearUsuario(String nombre,String mail,String nick,String password);
    
}
