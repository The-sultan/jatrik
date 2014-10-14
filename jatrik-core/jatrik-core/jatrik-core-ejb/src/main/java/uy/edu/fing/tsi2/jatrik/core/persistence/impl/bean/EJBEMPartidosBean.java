package uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Formacion;
import uy.edu.fing.tsi2.jatrik.core.domain.JugadorEnFormacion;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEstadoPartido;
import uy.edu.fing.tsi2.jatrik.core.persistence.IEMPartidos;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMPartidosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.remote.EJBEMPartidosRemote;


@Stateless
@Local(EJBEMPartidosLocal.class)
@Remote(EJBEMPartidosRemote.class)
public class EJBEMPartidosBean implements IEMPartidos {

	@PersistenceContext(name = "Jatrik-ejbPU")
	private EntityManager entityManager;
	
	@EJB
	EJBEMEquiposLocal EquiposEJB;
	
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

	@SuppressWarnings("unchecked")
	public List<Partido> findPartidosPorFechayEstados(Date fechaDesde,
			Date fechaHasta, Set<EnumEstadoPartido> estados) {
		
		Query consulta = this.crearConsulta(fechaDesde, fechaHasta, estados);
		List<Partido> res = consulta.getResultList();
		return res;
	}
	
	private Query crearConsulta(Date fechaDesde, Date fechaHasta, Set<EnumEstadoPartido> estados) {
		StringBuffer consultaString = new StringBuffer();
		boolean existenCondiciones = false;

		if (estados != null) {
			int cantEstados = estados.size();
			if (cantEstados > 0) {
				if (existenCondiciones) {
					consultaString.append("and ");
				}
				if (cantEstados == 1) {
					consultaString.append("p.estado = :argEstado ");
				} else {
					consultaString.append("p.estado in(");
					int i;
					for (i = 1; i < cantEstados; i++) {
						consultaString.append(":argEstado" + i + ", ");
					}
					consultaString.append(":argEstado" + i + ") ");
				}
				existenCondiciones = true;
			}
		}

		if (fechaDesde != null) {
			if (existenCondiciones) {
				consultaString.append("and ");
			}
			consultaString.append("p.fecha >= :argFechaDesde ");
			existenCondiciones = true;
		}
		if (fechaHasta != null) {
			if (existenCondiciones) {
				consultaString.append("and ");
			}
			consultaString.append("p.fecha <= :argFechaHasta ");
			existenCondiciones = true;
		}

		String clausulaFrom = "";
		clausulaFrom = existenCondiciones ? "select p from " + Partido.class.getName() + " p where " : "select p from " + Partido.class.getName() + " p ";
		consultaString.insert(0, clausulaFrom).append("order by p.fecha");

		Query consulta = entityManager.createQuery(consultaString.toString());

		if (estados != null) {
			if (estados.size() > 0) {
				if (estados.size() == 1) {
					consulta.setParameter("argEstado", estados);
				} else {
					int i = 1;
					for (EnumEstadoPartido estado : estados) {
						consulta.setParameter("argEstado" + i, estado);
						i++;
					}
				}
			}
		}
		if (fechaDesde != null) {
			consulta.setParameter("argFechaDesde", fechaDesde);
		}
		if (fechaHasta != null) {
			consulta.setParameter("argFechaHasta", fechaHasta);
		}

		return consulta;
	}

	@Override
	public void inicializarPartido(Partido partido){
		Equipo equipoLocal = EquiposEJB.find(partido.getLocal().getId());
		Equipo equipoVisitante = EquiposEJB.find(partido.getVisitante().getId());
		
		Formacion formacionLocal = clonarFormacion(equipoLocal.getFormacion());
		Formacion formacionVisitante = clonarFormacion(equipoVisitante.getFormacion());
		partido.setFormacionLocal(formacionLocal);
		partido.setFormacionVisitante(formacionVisitante);
		entityManager.persist(formacionLocal);
		entityManager.persist(formacionVisitante);
		partido.setFormacionVisitante(clonarFormacion(equipoVisitante.getFormacion()));
		partido.setEstado(EnumEstadoPartido.EN_CURSO);
		partido.setMinuto(0);
		
		entityManager.merge(partido);
		
	}
	
	private Formacion clonarFormacion(Formacion formacion){
		Formacion formacionClone = new Formacion();
		Set<JugadorEnFormacion> jugadoresClone = new HashSet<>();
		for(JugadorEnFormacion jugadorEnFormacion : formacion.getJugadores()){
			JugadorEnFormacion jugadorClone = new JugadorEnFormacion(jugadorEnFormacion.getJugador(), 
					jugadorEnFormacion.getIndice(), jugadorEnFormacion.getPuestoFormacion(), formacionClone);
			jugadoresClone.add(jugadorClone);
		}
		formacionClone.setJugadores(jugadoresClone);
		return formacionClone;
	}
	
}

