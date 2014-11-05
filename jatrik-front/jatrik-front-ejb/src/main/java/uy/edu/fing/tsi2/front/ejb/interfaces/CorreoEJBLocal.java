package uy.edu.fing.tsi2.front.ejb.interfaces;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoCorreo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;

public interface CorreoEJBLocal {

	List<InfoCorreo> obtenerCorreos(InfoUsuario usuario);

	void addCorreo(InfoCorreo nuevoCorreo);

	void updateCorreo(InfoCorreo c);

}
