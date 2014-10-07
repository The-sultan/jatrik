package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.ejb.IPartidos;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerPartidoLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerPartidoRemote;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMPartidosLocal;

/**
 *
 * @author c753388
 */
@Stateless
@Local(EJBManagerPartidoLocal.class)
@Remote(EJBManagerPartidoRemote.class)
public class EJBManagerPartidoBean implements IPartidos {

	private static final Logger logger = Logger
			.getLogger(EJBManagerPartidoBean.class);

	@EJB
	private EJBEMPartidosLocal partidos;


	public List<Partido> obtenerPartidos(Long idEquipo) {
		try {
			logger.info("Voy a buscar los partidos del equipo " + idEquipo);
			List<Partido> prt = partidos.findPartidosDeEquipo(idEquipo);
			logger.info("Obtuve los partidos");
			return prt;			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;

		}
	}

	
}
