package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;
import uy.edu.fing.tsi2.jatrik.core.ejb.IUsuarios;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerUsuarioLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerUsuarioRemote;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMUsuariosLocal;

/**
 *
 * @author c753388
 */
@Stateless
@Local(EJBManagerUsuarioLocal.class)
@Remote(EJBManagerUsuarioRemote.class)
public class EJBManagerUsuarioBean implements IUsuarios {

	private static final Logger logger = Logger
			.getLogger(EJBManagerUsuarioBean.class);

	@EJB
	private EJBEMUsuariosLocal usuarios;

	@EJB
	private EJBEMEquiposLocal equipos;
	
	@EJB
	private EJBEMJugadoresLocal jugadores;

	public Long crearUsuario(String nick, String nombre, String mail,
			String password, String nombreEquipo, double longitud,
			double latitud, int altura, String nombreEstadio) {
		try {

				// TODO Aca deberiamos controlar si no hay equipos libres o si no hay mas jugadores
					Usuario usr = new Usuario();
					usr.setNick(nick);
					usr.setNombre(nombre);
					usr.setEmail(mail);
					usr.setPassword(password);
					
		
					Equipo equipo = equipos.findEquipoLibre();
					equipo.setLatitud(latitud);
					equipo.setLongitud(longitud);
					equipo.setAltura(altura);
					equipo.setNombre(nombreEquipo);
					equipo.setEstadio(nombreEstadio);
					
					equipo.setUsuario(usr);
					usr.setEquipo(equipo);
					usuarios.add(usr);
		
					return usr.getId();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;

		}
	}

}
