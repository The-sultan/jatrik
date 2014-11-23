package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoFormacion;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Formacion;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.JugadorEnFormacion;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;
import uy.edu.fing.tsi2.jatrik.core.ejb.IEquipos;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEquipoBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerEquipoBeanRemote;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEstadoPartido;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoFormacion;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoJugador;
import uy.edu.fing.tsi2.jatrik.core.exceptions.JatrikPersistenceException;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMDatosJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMPartidosLocal;

@Stateless
@Local(EJBManagerEquipoBeanLocal.class)
@Remote(EJBManagerEquipoBeanRemote.class)
public class EJBManagerEquipoBean implements IEquipos {
	
	@EJB
	EJBEMEquiposLocal equiposEJB;
	
	@EJB
	EJBEMPartidosLocal partidosEJB;
	
	@EJB
	private EJBEMEquiposLocal equipos;

	@EJB
	private EJBEMJugadoresLocal jugadores;

	public Equipo find(Long id){
		return equiposEJB.find(id);
	}
	
	@Override
	public Formacion getFormacionEstandar(Long id){
		Equipo equipo = equiposEJB.find(id);
		return equipo.getFormacion();
	}
	
	@Override
	public Formacion getFormacionProximoPartido(Long id){
		Partido proximoPartido = getProximoPartido(id);
		if(proximoPartido == null){
			throw( new JatrikPersistenceException("no hay proximo partido"));
		}else{
			return proximoPartido.getLocal().getId().equals(id) ? proximoPartido.getFormacionLocal() 
				: proximoPartido.getFormacionVisitante();
		}
	}

	@Override
	public void storeFormacionEstandar(Long id, InfoFormacion infoFormacion) {
		Equipo equipo = equiposEJB.find(id);
		Formacion formacion = equipo.getFormacion();
		if(formacion == null){
			formacion = new Formacion();
		}else{
			equiposEJB.deleteFormacion(formacion);
		}
		
		Set<JugadorEnFormacion> jugadoresEnFormacion = generarJugadoresEnTodasLasPosiciones(infoFormacion, formacion);
		
		formacion.setJugadores(jugadoresEnFormacion);
		formacion.setDescriptor(infoFormacion.getDescriptor());
		equipo.setFormacion(formacion);
		equiposEJB.updateFormacion(formacion);
		equiposEJB.update(equipo);
	}

	@Override
	public void storeFormacionProximoPartido(Long id, InfoFormacion infoFormacion) {
		Partido partido = getProximoPartido(id);
		if(partido == null)
			throw( new JatrikPersistenceException("no hay proximo partido"));
		boolean esLocal = id.equals(partido.getLocal().getId());
		Formacion formacion = esLocal ? partido.getFormacionLocal() : partido.getFormacionVisitante();
		if(formacion == null){
			formacion = new Formacion();
		}else{
			equiposEJB.deleteFormacion(formacion);
		}
		Set<JugadorEnFormacion> jugadoresEnFormacion = generarJugadoresEnTodasLasPosiciones(infoFormacion, formacion);
		formacion.setJugadores(jugadoresEnFormacion);
		formacion.setDescriptor(infoFormacion.getDescriptor());
		equiposEJB.updateFormacion(formacion);
		if(esLocal){
			partido.setFormacionLocal(formacion);
		}else{
			partido.setFormacionVisitante(formacion);
		}
		partidosEJB.update(partido);
	}
	
	private Partido getProximoPartido(Long equipoId){
		List<Partido> partidos = partidosEJB.findPartidosDeEquipo(equipoId, EnumEstadoPartido.PENDIENTE);
		if(partidos.isEmpty()){
			return null;
		}
		Collections.sort(partidos, new Comparator<Partido>() {
			public int compare(Partido p1, Partido p2) {
				return p1.getFechaInicio().compareTo(p2.getFechaInicio());
			}
		});
		return partidos.get(0);
	}
	
	
	public Set<JugadorEnFormacion> generarJugadoresEnTodasLasPosiciones(InfoFormacion infoFormacion,
			Formacion formacion){
		Set<JugadorEnFormacion> jugadoresEnFormacion = new HashSet<>();
		List<InfoJugador> goleros = new ArrayList<>();
		goleros.add(infoFormacion.getGolero());
		jugadoresEnFormacion.addAll(generarJugadoresEnFormacion(goleros,
				EnumPuestoFormacion.ARQUERO, formacion));
		jugadoresEnFormacion.addAll(generarJugadoresEnFormacion(infoFormacion.getDefensas(),
				EnumPuestoFormacion.DEFENSA, formacion));
		jugadoresEnFormacion.addAll(generarJugadoresEnFormacion(infoFormacion.getDelanteros(),
				EnumPuestoFormacion.DELANTERO, formacion));
		jugadoresEnFormacion.addAll(generarJugadoresEnFormacion(infoFormacion.getMediocampistas(),
				EnumPuestoFormacion.MEDIOCAMPISTA, formacion));
		jugadoresEnFormacion.addAll(generarJugadoresEnFormacion(infoFormacion.getSuplentes(),
				EnumPuestoFormacion.SUPLENTE, formacion));
		jugadoresEnFormacion.addAll(generarJugadoresEnFormacion(infoFormacion.getReservas(),
				EnumPuestoFormacion.RESERVA, formacion));
		return jugadoresEnFormacion;
	}
	
	private List<JugadorEnFormacion> generarJugadoresEnFormacion(List<InfoJugador> jugadores,
			EnumPuestoFormacion puesto, Formacion formacion){
		List<JugadorEnFormacion> jugadoresEnFormacion = new ArrayList<>();
		int i = 0;
		for(InfoJugador infoJugador : jugadores){
			Jugador jugador = new Jugador();
			jugador.setId(infoJugador.getId());
			JugadorEnFormacion jugadorEnFormacion = new JugadorEnFormacion(jugador, i, puesto, formacion);
			jugadoresEnFormacion.add(jugadorEnFormacion);
			i++;
		}
		return jugadoresEnFormacion;
	}
	
	public List<Jugador> obtenerJugadoresEquipo(Long id){
		List<Jugador> resultado = new ArrayList<Jugador>();	
		for (Jugador jugador : equiposEJB.find(id).getJugadores()) {
			if (!jugador.getEnVenta()){
				//Si no esta ya en venta lo agrego al resultado
				resultado.add(jugador);
			}
		}
		
		return resultado;
			
	}

	@Override
	public Equipo crearEquipo(InfoEquipo infoEquipo, Usuario usuario) {
		Equipo equipo = new Equipo();
		equipo.setNombre(infoEquipo.getNombre());
		equipo.setEstadio(infoEquipo.getEstadio().getNombre());
		// infoUsuario.getInfoEquipo());

		// Seteo el Usuario al Equipo
		if(usuario != null){
			equipo.setUsuario(usuario);
		}
		inicializarEquipo(equipo);
		return equipo;
	}
	
	private void inicializarEquipo(Equipo equipo){
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
}
