package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.DatosJugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Habilidad;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;
import uy.edu.fing.tsi2.jatrik.core.ejb.IUsuarios;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerUsuarioLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerUsuarioRemote;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumHabilidad;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoJugador;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMDatosJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMUsuariosLocal;

/**
 *
 * @author c753388
 */

@Local(value = EJBManagerUsuarioLocal.class)
@Remote(value = EJBManagerUsuarioRemote.class)
@Stateless
public class EJBManagerUsuarioBean implements IUsuarios {

	private static final Logger logger = Logger
			.getLogger(EJBManagerUsuarioBean.class);

	@EJB
	private EJBEMUsuariosLocal usuarios;

	@EJB
	private EJBEMEquiposLocal equipos;

	@EJB
	private EJBEMJugadoresLocal jugadores;

	@EJB
	private EJBEMDatosJugadoresLocal datosJugadores;

	@Override
	public Long crearUsuario(String nombre, String nick, String email, String password,
	String nombreEquipo, String nombreEstadio) {
		try {
			logger.info("Voy a Validar al usuario");
			if (usuarios.findUsuarioByNick(nick) == null) {

				// TODO Aca deberiamos controlar si no hay equipos libres o si
				// no
				// hay mas jugadores
				Usuario usr = new Usuario();
				usr.setNombre(nombre);
				usr.setEmail(email);
				usr.setPassword(password);
				usr.setNick(nick);
				// BeanUtils.copyProperties(usr, infoUsuario);

				// Equipo equipo = equipos.findEquipoLibre();
				// equipo.setLatitud(latitud);
				// equipo.setLongitud(longitud);
				// equipo.setAltura(altura);
				// equipo.setNombre(nombreEquipo);
				// equipo.setEstadio(nombreEstadio);
				// equipo.setUsuario(usr);
				// equipos.update(equipo);

				Equipo equipo = new Equipo();
				equipo.setNombre(nombreEquipo);
				equipo.setEstadio(nombreEstadio);
				// infoUsuario.getInfoEquipo());

				// Seteo el Usuario al Equipo
				equipo.setUsuario(usr);

				inicializaEquipo(equipo);
				usr.setEquipo(equipo);
				usuarios.add(usr);
				logger.info("Usuario Creado");
				return usr.getId();
			} else {
				// Ver que retornamos al servicio rest
				logger.info("El usuario ya Existe");
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;

		}
	}

	public void inicializaEquipo(Equipo equipo) {

		Map<String, String> mapNombres = new HashMap<String, String>();
		List<Jugador> players = new ArrayList<Jugador>();
		Jugador jugador = null;
		int numeroCamiseta = 1;
		Habilidad habilidad = null;

		List<DatosJugador> nombresJugadores = datosJugadores.findAll();

		while (players.size() < 18) {

			Random r = new Random();
			int random = r.nextInt(nombresJugadores.size());

			DatosJugador datosJugador = nombresJugadores.get(random);

			// Me fijo si el nombre no lo usé aún para este equipo.
			if (mapNombres.get(datosJugador.getNombre()) == null) {

				mapNombres.put(datosJugador.getNombre(),
						datosJugador.getNombre());

				jugador = new Jugador();
				jugador.setNombre(datosJugador.getNombre());
				jugador.setNro_Camiseta(numeroCamiseta);

				jugador.setEdad(r.nextInt(20) + 20); // edad entre 20 y 39 años.
				jugador.setAltura(r.nextInt(155) + 40); // altura entre 155 y
														// 195
				jugador.setPeso(r.nextInt(70) + 25); // peso entre 70 y 85

				jugador.setEnVenta(new Boolean(false));

				// 15 jugadores - 2 arqueros, 5 defensas, 5 volantes y 3
				// delanteros.
				if (players.size() < 2) {
					jugador.setPuesto(EnumPuestoJugador.ARQUERO);
				} else if (players.size() < 8) {
					jugador.setPuesto(EnumPuestoJugador.DEFENSA);
				} else if (players.size() < 14) {
					jugador.setPuesto(EnumPuestoJugador.MEDIOCAMPISTA);
				} else {
					jugador.setPuesto(EnumPuestoJugador.DELANTERO);
				}

				// Habilidades del jugador entre 30 y 70.
				List<Habilidad> habilidades = new LinkedList<Habilidad>();
				habilidad = new Habilidad(r.nextInt(40) + 30,
						EnumHabilidad.VELOCIDAD, "Velocidad");
				habilidades.add(habilidad);

				habilidad = new Habilidad(r.nextInt(40) + 30,
						EnumHabilidad.PORTERIA, "Porteria");
				habilidades.add(habilidad);

				habilidad = new Habilidad(r.nextInt(40) + 30,
						EnumHabilidad.DEFENSA, "Defensa");
				habilidades.add(habilidad);

				habilidad = new Habilidad(r.nextInt(40) + 30,
						EnumHabilidad.ATAQUE, "Ataque");
				habilidades.add(habilidad);

				habilidad = new Habilidad(r.nextInt(40) + 30,
						EnumHabilidad.TECNICA, "Tecnica");
				habilidades.add(habilidad);

				jugador.setHabilidades(habilidades);

				jugador.setEquipo(equipo);

				jugadores.add(jugador);
				players.add(jugador);

				numeroCamiseta++;

			}

		}

		equipo.setJugadores(new HashSet<Jugador>(players));
		// Guardo en Base
		equipos.add(equipo);

	}

	public void inicializaEquipo2(Equipo equipo) {
		Random r = new Random((new Date()).getTime());
		int random;
		List<Jugador> players = new ArrayList<Jugador>();
		Jugador player;
		int camisetaTitular = 1;
		int camisetaSuplente = 12;

		// 2 GOLEROS
		List<Jugador> golerosLibres = jugadores
				.findJugadoresPuestoLibres(EnumPuestoJugador.ARQUERO);

		random = r.nextInt(golerosLibres.size());
		player = golerosLibres.get(random);
		golerosLibres.remove(random);
		player.setNro_Camiseta(camisetaTitular);
		camisetaTitular++;
		players.add(player);

		random = r.nextInt(golerosLibres.size());
		player = golerosLibres.get(random);
		golerosLibres.remove(random);
		player.setNro_Camiseta(camisetaSuplente);
		camisetaSuplente++;
		players.add(player);

		// DEFENSAS
		List<Jugador> defensasLibres = jugadores
				.findJugadoresPuestoLibres(EnumPuestoJugador.DEFENSA);
		// 4 DEFENSAS TITULARES
		for (int i = 1; i <= 4; i++) {
			random = r.nextInt(defensasLibres.size());
			player = defensasLibres.get(random);
			defensasLibres.remove(random);
			player.setNro_Camiseta(camisetaTitular);
			camisetaTitular++;
			players.add(player);
		}
		// 2 DEFENSAS TITULARES
		for (int i = 1; i <= 2; i++) {
			random = r.nextInt(defensasLibres.size());
			player = defensasLibres.get(random);
			defensasLibres.remove(random);
			player.setNro_Camiseta(camisetaSuplente);
			camisetaSuplente++;
			players.add(player);
		}

		// MEDIOCAMPISTAS
		List<Jugador> mediocampistasLibres = jugadores
				.findJugadoresPuestoLibres(EnumPuestoJugador.MEDIOCAMPISTA);
		// 3 MEDIOCAMPISTAS TITULARES
		for (int i = 1; i <= 3; i++) {
			random = r.nextInt(mediocampistasLibres.size());
			player = mediocampistasLibres.get(random);
			mediocampistasLibres.remove(random);
			player.setNro_Camiseta(camisetaTitular);
			camisetaTitular++;
			players.add(player);
		}
		// 3 MEDIOCAMPISTAS TITULARES
		for (int i = 1; i <= 3; i++) {
			random = r.nextInt(mediocampistasLibres.size());
			player = mediocampistasLibres.get(random);
			mediocampistasLibres.remove(random);
			player.setNro_Camiseta(camisetaSuplente);
			camisetaSuplente++;
			players.add(player);
		}

		// DELANTEROS
		List<Jugador> delanterosLibres = jugadores
				.findJugadoresPuestoLibres(EnumPuestoJugador.DELANTERO);
		// 3 DELANTEROS TITULARES
		for (int i = 1; i <= 3; i++) {
			random = r.nextInt(delanterosLibres.size());
			player = delanterosLibres.get(random);
			delanterosLibres.remove(random);
			player.setNro_Camiseta(camisetaTitular);
			camisetaTitular++;
			players.add(player);
		}
		// 2 DELANTEROS TITULARES
		for (int i = 1; i <= 2; i++) {
			random = r.nextInt(delanterosLibres.size());
			player = delanterosLibres.get(random);
			delanterosLibres.remove(random);
			player.setNro_Camiseta(camisetaSuplente);
			camisetaSuplente++;
			players.add(player);
		}

		// Le asigno el equipo al jugador
		for (Jugador jugador : players) {
			jugador.setEquipo(equipo);
			jugadores.update(jugador);
		}

		//Le asigno los jugadores al equipo
		equipo.setJugadores(new HashSet<Jugador>(players));
		// Guardo en Base
		equipos.add(equipo);

	}

	public Usuario validarUsuario(String nick, String password) {
		Usuario usuario = this.usuarios.findUsuarioByNick(nick);
		if (usuario != null) {
			if (usuario.getNick().equals(nick)
					&& usuario.getPassword().equals(password)) {
				return usuario;
			}
		}
		return null;
	}
	
	public List<Usuario> obtenerUsuarios(){
		return usuarios.findAll();
	}
}
