package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMEquipos;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMEquiposRemote;


@Stateless
@Local(value = EJBEMEquiposLocal.class)
@Remote(value = EJBEMEquiposRemote.class)
public class EJBEMEquiposBean implements IEMEquipos {

	
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

	public Equipo find(Long id) {
		return entityManager.find(Equipo.class, id);
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
		return ((Equipo) consulta.getResultList().get(0));

	}
}
