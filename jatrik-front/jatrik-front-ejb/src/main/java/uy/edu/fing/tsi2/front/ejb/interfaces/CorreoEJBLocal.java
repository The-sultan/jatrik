package uy.edu.fing.tsi2.front.ejb.interfaces;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoCorreo;

public interface CorreoEJBLocal {

	
	void addCorreo(InfoCorreo nuevoCorreo);

	void updateCorreo(InfoCorreo c);

	List<InfoCorreo> obtenerCorreos(Long idUsuario);

}
