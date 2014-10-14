package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tsi2.jatrik.core.domain.Comentario;
import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMComentarios;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMComentariosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMComentariosRemote;


@Stateless
@Local(value = EJBEMComentariosLocal.class)
@Remote(value = EJBEMComentariosRemote.class)
public class EJBEMComentariosBean implements IEMComentarios {


	@PersistenceContext(name = "Jatrik-ejbPU")
	private EntityManager entityManager;
	
		
	public Comentario add(Comentario jugador) {				
		entityManager.persist(jugador);
		return jugador;
	}
	
	public Comentario update(Comentario jugador) {
		entityManager.merge(jugador);
		return jugador;
	}
	
	public void delete(Comentario jugador) {		
		entityManager.remove(entityManager.merge(jugador));
	}

	@Override
	public Comentario find(Long id) {
		Comentario comentario = entityManager.find(Comentario.class, id);
		return comentario;
	}
		
	@SuppressWarnings("unchecked")
	public List<Comentario> findAll() {
		Query consulta = entityManager.createQuery("select h from " + Comentario.class.getName() + " h");
		return (List<Comentario>) consulta.getResultList();
	}
   
	@SuppressWarnings("unchecked")
	public List<Comentario> findComentariosByEventoAndNivel(Evento evento, 
			Long nivel) {

		Query consulta = entityManager.createNamedQuery("findComnetarioByEventoAndNivel");
		consulta.setParameter("evento", evento);
		consulta.setParameter("nivel", nivel);
		return ((List<Comentario>) consulta.getResultList());

	}
	
}

