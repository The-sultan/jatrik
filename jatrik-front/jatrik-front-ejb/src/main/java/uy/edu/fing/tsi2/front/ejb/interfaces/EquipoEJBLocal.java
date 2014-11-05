package uy.edu.fing.tsi2.front.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamientoJugador;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoFormacion;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;

/**
 *
 * @author c753388
 */
@Local
public interface EquipoEJBLocal {

	public InfoEquipo getEquipo(long id);

	String entrenarEquipo(long id, List<InfoEntrenamientoJugador> jugadores);
	
	public InfoFormacion getFormacionEstandar(long id);
	
	public InfoFormacion getFormacionProximoPartido(long id);
	
	public void saveFormacionEstandar(long id, InfoFormacion infoFormacion);
	
	public void saveFormacionProximoPartido(long id, InfoFormacion infoFormacion);

	public List<InfoJugador> getJugadoresEquipo(Long id);

}
