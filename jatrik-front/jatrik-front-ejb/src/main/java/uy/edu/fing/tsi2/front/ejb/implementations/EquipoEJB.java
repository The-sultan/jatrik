package uy.edu.fing.tsi2.front.ejb.implementations;


import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.exceptions.RestClientException;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamiento;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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
	public String entrenarEquipo(long id, int modo) {
		InfoEntrenamiento ent = new InfoEntrenamiento();
		ent.setIdEquipo((int)id);
		//ent.setModo(modo);
		try {
			return jatrikCoreClient.postEntrenamiento(ent);
		} catch (RestClientException e) {
			throw e;
		}
	}
}
