package uy.edu.fing.tsi2.front.ejb.interfaces;

import javax.ejb.Local;

/**
 *
 * @author c753388
 */
@Local
public interface EquipoEJBLocal {
	
	 public Long getEquipo(String id);
    
}
