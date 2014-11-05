package uy.edu.fing.tsi2.front.ejb.implementations;


import java.util.List;

import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.exceptions.RestClientException;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamiento;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamientoJugador;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoFormacion;

/**
 *
 * @author c753388
 */
@Stateless
public class EquipoEJB implements EquipoEJBLocal {

	@EJB
	private RestClientEJBLocal jatrikCoreClient;

	@Override
	public InfoEquipo getEquipo(long id) {
		
		try {
			return jatrikCoreClient.getEquipo(id);
		} catch (RestClientException e) {
			throw e;
		}
	}
	
	@Override
	public String entrenarEquipo(long id, List<InfoEntrenamientoJugador> jugadores) {
		InfoEntrenamiento ent = new InfoEntrenamiento();
		ent.setIdEquipo((int)id);
		ent.setJugadores(jugadores);
		try {
			return jatrikCoreClient.postEntrenamiento(ent);
		} catch (RestClientException e) {
			throw e;
		}
	}

	@Override
	public InfoFormacion getFormacionEstandar(long id) {
		return jatrikCoreClient.getFormacionEstandar(id);
	}

	@Override
	public InfoFormacion getFormacionProximoPartido(long id) {
		return jatrikCoreClient.getFormacionProximoPartido(id);
	}

	@Override
	public void saveFormacionEstandar(long id, InfoFormacion infoFormacion) {
		jatrikCoreClient.storeFormacionEstandar(id, infoFormacion);
	}

	@Override
	public void saveFormacionProximoPartido(long id, InfoFormacion infoFormacion) {
		jatrikCoreClient.storeFormacionProximoPartido(id, infoFormacion);
	}
	
	@Override
	public List<InfoJugador> getJugadoresEquipo(Long id){
		return jatrikCoreClient.getJugadoresEquipo(id);
	}
}
