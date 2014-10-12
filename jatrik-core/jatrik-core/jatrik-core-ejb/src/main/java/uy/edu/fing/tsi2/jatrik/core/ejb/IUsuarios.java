package uy.edu.fing.tsi2.jatrik.core.ejb;

import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;

public interface IUsuarios {

	Long crearUsuario(String nombre, String nick, String email, String password);
	
	Usuario validarUsuario(String nick,String password);
}
