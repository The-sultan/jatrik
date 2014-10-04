package uy.edu.fing.tsi2.jatrik.core.ejb;

public interface IUsuarios {

	Long crearUsuario(String nick, String nombre, String mail,
			String password, String nombreEquipo, double longitud,
			double latitud, int altura, String nombreEstadio);
}
