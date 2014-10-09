package uy.edu.fing.tsi2.front.ejb.rest.client.interfaces;

import javax.ejb.Local;
import uy.edu.fing.tsi2.front.ejb.rest.client.exceptions.RestClientException;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;

/**
 * @author Farid
 */
@Local
public interface RestClientEJBLocal {
	
	public String postNuevoUsuario(InfoUsuario usuario) throws RestClientException;

}
