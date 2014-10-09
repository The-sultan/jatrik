package uy.edu.fing.tsi2.front.ejb.implementations;


import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;

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
	public Long getEquipo(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
    
    
}
