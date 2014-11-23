package uy.edu.fing.tsi2.jatrik.core.ejb;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.Correo;

public interface ICorreos {

	Correo addCorreo(Long to,Long from,String asunto,String mensaje);
	void deleteCorreo(Long Correo);
	Correo findCorreo(Long id);
	Correo leerCorreo(Long id);
	List<Correo> obtenerCorreos(Long usuarioId);
        List<Correo> obtenerCorreosEnviados(Long usuarioId);
	
}
