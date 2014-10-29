package uy.edu.fing.tsi2.jatrik.core.ejb;

import java.util.List;
import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;

public interface IUsuarios {

	Long crearUsuario(String nombre, String nick, String email, String password, String nombreEquipo, String nombreEstadio);
	
	Usuario validarUsuario(String nick,String password);
	
	List<Usuario> obtenerUsuarios();
}
