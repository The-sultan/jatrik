package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

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

    public Liga add(Liga liga) {
        entityManager.persist(liga);
        return liga;
    }

    public Liga update(Liga liga) {
        entityManager.merge(liga);
        return liga;
    }

    public void delete(Liga liga) {
        entityManager.remove(entityManager.merge(liga));
    }

    public Liga find(Long id) {
        return entityManager.find(Liga.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Liga> findAll() {
        Query consulta = entityManager.createQuery("select h from " + Liga.class.getName() + " h");
        return (List<Liga>) consulta.getResultList();
    }

    public Liga findLigaByEquipo(Equipo equipo) {
        Query consulta = entityManager.createNamedQuery("buscarLigaPorEquipo");
        consulta = consulta.setParameter("argEquipoId", equipo.getId());
        try {
            return (Liga) consulta.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Liga findLigaByPartido(Partido partido) {

        Query consulta = entityManager.createNamedQuery("buscarLigaPorPartido");
        consulta = consulta.setParameter("argPartidoId", partido.getId());
        try {
            return (Liga) consulta.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
