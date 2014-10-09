package uy.edu.fing.tsi2.jatrik.core.ejb;

public interface IUsuarios {

	Long crearUsuario(String nombre, String nick, String email, String password);
}
