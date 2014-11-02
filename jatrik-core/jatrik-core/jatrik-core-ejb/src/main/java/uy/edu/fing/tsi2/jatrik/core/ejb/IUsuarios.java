package uy.edu.fing.tsi2.jatrik.core.ejb;

import java.util.List;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;

import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;

public interface IUsuarios {

	Long crearUsuario(InfoUsuario infoUsuario);
	
	Usuario validarUsuario(String nick,String password);
	
	List<Usuario> obtenerUsuarios();
	
	boolean registrationIdUsuario(Long idUsuario, String registrationId);
	
	void EnviarMensajePush(Long idUsuario, String mensaje);
}
