package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEstadoPartido;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMPartidos;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMPartidosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMPartidosRemote;


@Stateless
@Local(EJBEMPartidosLocal.class)
@Remote(EJBEMPartidosRemote.class)
public class EJBEMPartidosBean implements IEMPartidos {

	@PersistenceContext(name = "Jatrik-ejbPU")
	private EntityManager entityManager;
	
	public Partido add(Partido partido) {				
		entityManager.persist(partido);
		
		return partido;
	}
	
	public Partido update(Partido partido) {
		entityManager.merge(partido);
		return partido;
	}
	
	public void delete(Partido partido) {		
		entityManager.remove(entityManager.merge(partido));
	}

	public Partido find(Long id) {
		return entityManager.find(Partido.class, id);
	}
		
	@SuppressWarnings("unchecked")
	public List<Partido> findAll() {
		Query consulta = entityManager.createQuery("select h from " + Partido.class.getName() + " h");
		return (List<Partido>) consulta.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Partido> findPartidosDeEquipo(Long idEquipo) {
		EnumEstadoPartido Estado = EnumEstadoPartido.FINALIZADO;
		Query consulta = entityManager.createNamedQuery("findPartidosDeEquipo").setParameter("idEquipo", idEquipo).setParameter("estado", Estado);
		return ((List<Partido>) consulta.getResultList());

	}
}
