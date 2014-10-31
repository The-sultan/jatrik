package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.domain.eventos.EventoPartido;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMEventosPartidos;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEventosPartidosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMEventosPartidosRemote;



@Stateless
@Local(value = EJBEMEventosPartidosLocal.class)
@Remote(value = EJBEMEventosPartidosRemote.class)
public class EJBEMEventosPartidosBean implements IEMEventosPartidos {

	
	@PersistenceContext(name = "Jatrik-ejbPU")
	private EntityManager entityManager;
	
	
	
	
	public EventoPartido add(EventoPartido eventoPartido) {				
		entityManager.persist(eventoPartido);
		return eventoPartido;
	}
	
	public EventoPartido update(EventoPartido eventoPartido) {
		entityManager.merge(eventoPartido);
		return eventoPartido;
	}
	
	public void delete(EventoPartido eventoPartido) {		
		entityManager.remove(entityManager.merge(eventoPartido));
	}

	public EventoPartido find(Long id) {
		return entityManager.find(EventoPartido.class, id);
	}
	/*
	public EventoPartido findByName(String nombre) {
		Query queryUsuario = entityManager.createNamedQuery("findEventoByName");
	              queryUsuario.setParameter("nombre",nombre);
	              return (Evento) queryUsuario.getSingleResult();
	}
	*/	
	@SuppressWarnings("unchecked")
	public List<EventoPartido> findAll() {
		Query consulta = entityManager.createQuery("select h from " + Evento.class.getName() + " h");
		return (List<EventoPartido>) consulta.getResultList();
	}

	@Override
	public EventoPartido findByName(String nombre) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventoPartido> findByPartido(Long partidoId){
		Query query = entityManager.createNamedQuery("findEventosPartidoByPartido");
		Partido partido = new Partido();
		partido.setId(partidoId);
		query.setParameter("partido",partido);
		return (List<EventoPartido>) query.getResultList();
		
	}
}
