package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tsi2.jatrik.core.domain.Correo;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMCorreos;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMCorreosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMCorreosRemote;


@Stateless
@Local(value = EJBEMCorreosLocal.class)
@Remote(value = EJBEMCorreosRemote.class)
public class EJBEMCorreosBean implements IEMCorreos {

	@PersistenceContext(name = "Jatrik-ejbPU")
	private EntityManager entityManager;
	
	public Correo add(Correo jugador) {				
		entityManager.persist(jugador);
		return jugador;
	}
	
	public Correo update(Correo jugador) {
		entityManager.merge(jugador);
		return jugador;
	}
	
	public void delete(Correo jugador) {		
		entityManager.remove(entityManager.merge(jugador));
	}

	@Override
	public Correo find(Long id) {
		Correo Correo = entityManager.find(Correo.class, id);
		return Correo;
	}
		
	@SuppressWarnings("unchecked")
	public List<Correo> findAll() {
		Query consulta = entityManager.createQuery("select h from " + Correo.class.getName() + " h");
		return (List<Correo>) consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Correo> findCorreosUsuario(Long idUsuario) {
		Query consulta = entityManager.createNamedQuery("findCorreosUsuario");
		consulta.setParameter("id", idUsuario);
		return (List<Correo>)consulta.getResultList();

	}
        
        @SuppressWarnings("unchecked")
	public List<Correo> findCorreosUsuarioEnviados(Long idUsuario) {
		Query consulta = entityManager.createNamedQuery("findCorreosUsuarioEnviados");
		consulta.setParameter("id", idUsuario);
		return (List<Correo>)consulta.getResultList();

	}
}
