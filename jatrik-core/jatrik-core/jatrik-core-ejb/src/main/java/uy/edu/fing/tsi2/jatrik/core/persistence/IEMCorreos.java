package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.Correo;



public interface IEMCorreos {


	void delete(Correo Correo);

	Correo update(Correo Correo);

	Correo find(Long id);

	List<Correo> findAll();

	Correo add(Correo Correo);
	
	List<Correo> findCorreosUsuario(Long idUsuario);
        
        List<Correo> findCorreosUsuarioEnviados(Long idUsuario);
}