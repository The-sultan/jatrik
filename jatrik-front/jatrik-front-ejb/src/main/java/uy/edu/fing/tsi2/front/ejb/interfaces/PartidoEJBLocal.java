package uy.edu.fing.tsi2.front.ejb.interfaces;

import javax.ejb.Local;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoPartido;

@Local
public interface PartidoEJBLocal {
	public InfoPartido getInfoPartido(long idPartido);
}
