package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMEventos;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEventosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMEventosRemote;



@Stateless
@Local(value = EJBEMEventosLocal.class)
@Remote(value = EJBEMEventosRemote.class)
public class EJBEMEventoBean implements IEMEventos {

	
	@PersistenceContext(name = "Jatrik-ejbPU")
	private EntityManager entityManager;
	
		
	public Evento add(Evento jugador) {				
		entityManager.persist(jugador);
		return jugador;
	}
	
	public Evento update(Evento jugador) {
		entityManager.merge(jugador);
		return jugador;
	}
	
	public void delete(Evento jugador) {		
		entityManager.remove(entityManager.merge(jugador));
	}

	public Evento find(Long id) {
		return entityManager.find(Evento.class, id);
	}
	
	public Evento findByName(String nombre) {
		Query queryUsuario = entityManager.createNamedQuery("findEventoByName");
	              queryUsuario.setParameter("nombre",nombre);
	              return (Evento) queryUsuario.getSingleResult();
	}
		
	@SuppressWarnings("unchecked")
	public List<Evento> findAll() {
		Query consulta = entityManager.createQuery("select h from " + Evento.class.getName() + " h");
		return (List<Evento>) consulta.getResultList();
	}
   


}
