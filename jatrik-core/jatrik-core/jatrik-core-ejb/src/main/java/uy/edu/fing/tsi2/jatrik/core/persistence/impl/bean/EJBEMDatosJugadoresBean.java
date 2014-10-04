package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tsi2.jatrik.core.domain.DatosJugador;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMDatosJugadores;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMDatosJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMDatosJugadoresRemote;


@Stateless
@Local(value = EJBEMDatosJugadoresLocal.class)
@Remote(value = EJBEMDatosJugadoresRemote.class)
public class EJBEMDatosJugadoresBean implements IEMDatosJugadores {

	
	@PersistenceContext(name = "Jatrik-ejbPU")
	private EntityManager entityManager;
	
		
	public DatosJugador add(DatosJugador jugador) {				
		entityManager.persist(jugador);
		return jugador;
	}
	
	public DatosJugador update(DatosJugador jugador) {
		entityManager.merge(jugador);
		return jugador;
	}
	
	public void delete(DatosJugador jugador) {		
		entityManager.remove(entityManager.merge(jugador));
	}

	public DatosJugador find(Long id) {
		return entityManager.find(DatosJugador.class, id);
	}
		
	@SuppressWarnings("unchecked")
	public List<DatosJugador> findAll() {
		Query consulta = entityManager.createQuery("select h from " + DatosJugador.class.getName() + " h");
		return (List<DatosJugador>) consulta.getResultList();
	}
   


}
