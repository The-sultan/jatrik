package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoJugador;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMJugadores;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMJugadoresRemote;


@Stateless
@Local(value = EJBEMJugadoresLocal.class)
@Remote(value = EJBEMJugadoresRemote.class)
public class EJBEMJugadoresBean implements IEMJugadores {

	
	@PersistenceContext(name = "Jatrik-ejbPU")
	private EntityManager entityManager;
	
		
	public Jugador add(Jugador jugador) {				
		entityManager.persist(jugador);
		return jugador;
	}
	
	public Jugador update(Jugador jugador) {
		entityManager.merge(jugador);
		return jugador;
	}
	
	public void delete(Jugador jugador) {		
		entityManager.remove(entityManager.merge(jugador));
	}

	public Jugador find(Long id) {
		return entityManager.find(Jugador.class, id);
	}
		
	@SuppressWarnings("unchecked")
	public List<Jugador> findAll() {
		Query consulta = entityManager.createQuery("select h from " + Jugador.class.getName() + " h");
		return (List<Jugador>) consulta.getResultList();
	}
   
	@SuppressWarnings("unchecked")
	public List<Jugador> findJugadoresLibres() {

		Query consulta = entityManager.createNamedQuery("findJugadoresLibres");
		return ((List<Jugador>) consulta.getResultList());

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Jugador> findJugadoresPuestoLibres(EnumPuestoJugador puesto) {

		Query consulta = entityManager.createNamedQuery("findJugadoresPuestoLibres");
		consulta.setParameter("puesto", puesto);
		consulta.setMaxResults(50);
		return ((List<Jugador>) consulta.getResultList());

	}

	@SuppressWarnings("unchecked")
	public Set<Jugador> findJugadoresdelEquipo(Long idEquipo) {

		Query consulta = entityManager.createNamedQuery("findJugadoresdelEquipo");
		consulta.setParameter("idEquipo", idEquipo);
		Set<Jugador> foo = new HashSet<Jugador>(consulta.getResultList());
		return (foo);

	}
}
