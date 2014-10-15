package uy.edu.fing.tsi2.front.ejb.implementations;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.front.ejb.interfaces.PartidoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoPartido;

@Stateless
public class PartidoEJB implements PartidoEJBLocal {

	
	@EJB
	private RestClientEJBLocal jatrikCoreClient;
	
	@Override
	public InfoPartido getInfoPartido(long idPartido) {
		
		return jatrikCoreClient.getInfoPartido(idPartido);
	}

	@Override
	public void simularPartido(long idPartido) {
		jatrikCoreClient.simularPartido(idPartido);
	}

}
