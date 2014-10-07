package uy.edu.fing.tsi2.front.ejb.rest.client.interfaces;

import javax.ejb.Local;
import uy.edu.fing.tsi2.front.ejb.rest.client.exceptions.RestClientException;
import uy.edu.fing.tsi2.jatrik.common.payloads.Usuario;

/**
 * @author Farid
 */
@Local
public interface RestClientEJBLocal {
	
	public String postNuevoUsuario(Usuario usuario) throws RestClientException;

}
