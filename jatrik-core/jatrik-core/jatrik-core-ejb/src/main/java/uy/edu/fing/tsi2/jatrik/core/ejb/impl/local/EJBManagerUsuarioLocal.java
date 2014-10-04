package uy.edu.fing.tsi2.jatrik.core.ejb;

import javax.ejb.Local;

/**
 *
 * @author Farid
 */
@Local
public interface UsuarioEJBLocal {
	public Long crearUsuario(String nombre,String mail,String nick,String password);
	public Boolean findUsuarioByNickPassword(String nick,String pass);
	public boolean findUsuarioByUser(String nick);
}
