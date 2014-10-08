package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Formacion;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.JugadorEnFormacion;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuesto;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMEquipos;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMEquiposRemote;


@Stateless
@Local(value = EJBEMEquiposLocal.class)
@Remote(value = EJBEMEquiposRemote.class)
public class EJBEMEquiposBean implements IEMEquipos {

	
	private static final int CANTIDAD_DEFENSA_FORMACION_DEFAULT = 4;
	private static final int CANTIDAD_MEDIO_FORMACION_DEFAULT = 3;
	private static final int CANTIDAD_DELANTERA_FORMACION_DEFAULT = 3;
	
	@PersistenceContext(name = "Jatrik-ejbPU")
	private EntityManager entityManager;
	
		
	public Equipo add(Equipo jugador) {				
		entityManager.persist(jugador);
		return jugador;
	}
	
	public Equipo update(Equipo jugador) {
		entityManager.merge(jugador);
		return jugador;
	}
	
	public void delete(Equipo jugador) {		
		entityManager.remove(entityManager.merge(jugador));
	}

	@Override
	public Equipo find(Long id) {
		Equipo equipo = entityManager.find(Equipo.class, id);
		
		if(equipo.getFormacion() == null){
			generarFormacionPorDefecto(equipo);
		}
		return equipo;
	}
		
	@SuppressWarnings("unchecked")
	public List<Equipo> findAll() {
		Query consulta = entityManager.createQuery("select h from " + Equipo.class.getName() + " h");
		return (List<Equipo>) consulta.getResultList();
	}
   
	@SuppressWarnings("unchecked")
	public List<Equipo> findEquiposLibres() {

		Query consulta = entityManager.createNamedQuery("findEquiposLibres");
		return ((List<Equipo>) consulta.getResultList());

	}
	
	public Equipo findEquipoLibre() {

		Query consulta = entityManager.createNamedQuery("findEquipoLibre");
		//Devuelve el primero de la lista
		return ((Equipo) consulta.getResultList().get(0));

	}
	
	private void generarFormacionPorDefecto(Equipo equipo){
		Map<EnumPuesto,List<Jugador>> jugadoresMap = new HashMap<>();
		for(Jugador jugador : equipo.getJugadores()){
			List<Jugador> jugadores;
			if(jugadoresMap.containsKey(jugador.getPuesto())){
				jugadores = jugadoresMap.get(jugador.getPuesto());
			}
			else{
				jugadores = new ArrayList<>();
			}
			jugadores.add(jugador);
			jugadoresMap.put(jugador.getPuesto(),jugadores);
		}
		Formacion formacion = new Formacion();
		Set<JugadorEnFormacion> titulares = new HashSet<>();
		
		//elijo uno de todos los arqueros del cuadro de forma random
		Random generadorRandom = new Random();
		Jugador arquero = jugadoresMap.get(EnumPuesto.ARQUERO).get(generadorRandom.nextInt(jugadoresMap.get(EnumPuesto.ARQUERO).size()));
		Set<Jugador> utilizados = new HashSet<>();
		formacion.setArquero(arquero);
		formacion.setDefensas(obtenerJugadoresEnPosicion(jugadoresMap,EnumPuesto.DEFENSA,CANTIDAD_DEFENSA_FORMACION_DEFAULT, utilizados));
		formacion.setMediocampistas(obtenerJugadoresEnPosicion(jugadoresMap,EnumPuesto.MEDIOCAMPISTA,CANTIDAD_MEDIO_FORMACION_DEFAULT, utilizados));
		formacion.setDelanteros(obtenerJugadoresEnPosicion(jugadoresMap,EnumPuesto.DELANTERO,CANTIDAD_DELANTERA_FORMACION_DEFAULT, utilizados));
		
		Set<Jugador> jugadoresSinUtilizar = new HashSet<Jugador>(equipo.getJugadores());
		jugadoresSinUtilizar.removeAll(utilizados);
		
		Set<Jugador> suplentes = new HashSet<>();
		Iterator<Jugador> jugadorIter = jugadoresSinUtilizar.iterator();
		for(int i=0;i<7;i++){
			suplentes.add(jugadorIter.next());
		}
		formacion.setJugadoresSuplentes(suplentes);
		jugadoresSinUtilizar.removeAll(suplentes);
		formacion.setJugadoresReserva(jugadoresSinUtilizar);
		
		equipo.setFormacion(formacion);
		update(equipo);
		
		
		
		
	}
	
	private Set<JugadorEnFormacion> obtenerJugadoresEnPosicion(Map<EnumPuesto,List<Jugador>> jugadoresMap,
			EnumPuesto puesto, int cantidad, Set<Jugador> utilizados){
		Random generadorRandom = new Random();

		Set<JugadorEnFormacion> jugadorEnFormacionSeleccionados = new HashSet<>();
		for(int i=0;i<cantidad;i++){
			boolean agregaJugador = false;
			while(!agregaJugador){
				//obtiene un jugador al azar en el puesto proporcionado
				Jugador jugadorRandom = jugadoresMap.get(puesto).get(generadorRandom.nextInt(jugadoresMap.get(puesto).size()));
				//si no está en la cancha, seteo la variable agrega en true
				agregaJugador = !utilizados.contains(jugadorRandom);
				//si la variable esta en true lo agrego a dos conjuntos, el conjunto resultante y 
				//el conjunto auxiliar para no repetir dos veces al mismo jugador
				if(agregaJugador){
					utilizados.add(jugadorRandom);
					jugadorEnFormacionSeleccionados.add(new JugadorEnFormacion(jugadorRandom,i));
					
				}
			}
			
		}
		return jugadorEnFormacionSeleccionados;
	}
}
