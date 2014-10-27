package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tsi2.jatrik.core.domain.Transferencia;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMTransferencias;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMTransferenciasLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMTransferenciasRemote;


@Stateless
@Local(value = EJBEMTransferenciasLocal.class)
@Remote(value = EJBEMTransferenciasRemote.class)
public class EJBEMTransferenciasBean implements IEMTransferencias {

	
	@PersistenceContext(name = "Jatrik-ejbPU")
	private EntityManager entityManager;
	
		
	public Transferencia add(Transferencia Transferencia) {				
		entityManager.persist(Transferencia);
		return Transferencia;
	}
	
	public Transferencia update(Transferencia Transferencia) {
		entityManager.merge(Transferencia);
		return Transferencia;
	}
	
	public void delete(Transferencia Transferencia) {		
		entityManager.remove(entityManager.merge(Transferencia));
	}

	public Transferencia find(Long id) {
		return entityManager.find(Transferencia.class, id);
	}
		
	@SuppressWarnings("unchecked")
	public List<Transferencia> findAll() {
		Query consulta = entityManager.createQuery("select h from " + Transferencia.class.getName() + " h");
		return (List<Transferencia>) consulta.getResultList();
	}
   
	@SuppressWarnings("unchecked")
	public List<Transferencia> findTransferenciasLibres() {

		Query consulta = entityManager.createNamedQuery("findTransferenciasLibres");
		return ((List<Transferencia>) consulta.getResultList());

	}

	@SuppressWarnings("unchecked")
	public List<Transferencia> findTransferenciasdelEquipo(Long idEquipo) {

		Query consulta = entityManager.createNamedQuery("findTransferenciasdelEquipo");
		consulta.setParameter("idEquipo", idEquipo);
		return ((List<Transferencia>) consulta.getResultList());


	}
}
