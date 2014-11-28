package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;

import uy.edu.fing.tsi2.jatrik.core.domain.Liga;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMLigas;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMLigasLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMLigasRemote;

@Stateless
@Local(value = EJBEMLigasLocal.class)
@Remote(value = EJBEMLigasRemote.class)
public class EJBEMLigasBean implements IEMLigas {

    @PersistenceContext(name = "Jatrik-ejbPU")
    private EntityManager entityManager;

    @Override
    public Liga add(Liga liga) {
        entityManager.persist(liga);
        return liga;
    }

    @Override
    public Liga update(Liga liga) {
        entityManager.merge(liga);
        return liga;
    }

    @Override
    public void delete(Liga liga) {
        entityManager.remove(entityManager.merge(liga));
    }

    @Override
    public Liga find(Long id) {
        return entityManager.find(Liga.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Liga> findAll() {
        Query consulta = entityManager.createQuery("select h from " + Liga.class.getName() + " h");
        return (List<Liga>) consulta.getResultList();
    }

    @Override
    public Liga findLigaByEquipo(Equipo equipo) {
        Query consulta = entityManager.createNamedQuery("buscarLigaPorEquipo");
        consulta = consulta.setParameter("argEquipoId", equipo.getId());
        try {
            return (Liga) consulta.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Liga findLigaByPartido(Partido partido) {

        Query consulta = entityManager.createNamedQuery("buscarLigaPorPartido");
        consulta = consulta.setParameter("argPartidoId", partido.getId());
        try {
            return (Liga) consulta.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Liga> obtenerLigasEnCurso(){
        String sql = "SELECT t FROM " + Liga.class.getName() +
					" t WHERE t.fechaFin > :argFecha ";
	Query consulta = entityManager.createQuery(sql);
        consulta.setParameter("argFecha", new Date());
        try {
            return (List<Liga>) consulta.getResultList();
        } catch (NoResultException e) {
            return null;
        }      
    
    }
    
    @Override
    public List<Liga> obtenerLigasNoIniciados() {
        String sql = "SELECT t FROM " + Liga.class.getName() + 
				" t WHERE t.fechaInicio is null";
	Query consulta = entityManager.createQuery(sql);
        try {
            return (List<Liga>) consulta.getResultList();
        } catch (NoResultException e) {
            return null;
        }      
    }
}
