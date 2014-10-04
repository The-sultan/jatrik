package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMUsuarios;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMUsuariosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMUsuariosRemote;


@Stateless
@Local(value = EJBEMUsuariosLocal.class)
@Remote(value = EJBEMUsuariosRemote.class)
public class EJBEMUsuariosBean implements IEMUsuarios {

	
	@PersistenceContext(name = "Jatrik-ejbPU")
	private EntityManager entityManager;
	
		
	public Usuario add(Usuario usuario) {				
		entityManager.persist(usuario);
		
		return usuario;
	}
	
	public Usuario update(Usuario usuario) {
		entityManager.merge(usuario);
		return usuario;
	}
	
	public void delete(Usuario usuario) {		
		entityManager.remove(entityManager.merge(usuario));
	}

	public Usuario find(Long id) {
		return entityManager.find(Usuario.class, id);
	}
		
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll() {
		Query consulta = entityManager.createQuery("select h from " + Usuario.class.getName() + " h");
		return (List<Usuario>) consulta.getResultList();
	}
   
	 public Usuario findUsuarioByNick(String nick){
	       Usuario user = null;
	         try {
	              Query queryUsuario = entityManager.createNamedQuery("findUsuarioByNick");
	              queryUsuario.setParameter("nick",nick);
	              return (Usuario) queryUsuario.getSingleResult();
	         } catch (NoResultException e) {
	              return user;
	         }
	        
	    }
}
