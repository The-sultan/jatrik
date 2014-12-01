package uy.edu.fing.tsi2.front.ejb.interfaces;

import javax.ejb.Local;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoLiga;

@Local
public interface LigaEJBLocal {
	 InfoLiga obtenerInfoLiga(Long idEquipo);
}
