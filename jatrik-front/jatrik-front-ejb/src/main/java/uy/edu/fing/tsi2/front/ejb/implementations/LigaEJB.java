package uy.edu.fing.tsi2.front.ejb.implementations;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.front.ejb.interfaces.LigaEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoLiga;

@Stateless
public class LigaEJB implements LigaEJBLocal {
	
	@EJB
	private RestClientEJBLocal jatrikCoreClient;

	@Override
	public InfoLiga obtenerInfoLiga(Long idEquipo) {
		return jatrikCoreClient.getInformacionLiga(idEquipo);
	}
}
