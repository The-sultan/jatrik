package uy.edu.fing.tsi2.front.ejb.interfaces;

import javax.ejb.Local;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;

/**
 *
 * @author c753388
 */
@Local
public interface EquipoEJBLocal {

	public InfoEquipo getEquipo(long id);

	String entrenarEquipo(long id, int modo);

}
