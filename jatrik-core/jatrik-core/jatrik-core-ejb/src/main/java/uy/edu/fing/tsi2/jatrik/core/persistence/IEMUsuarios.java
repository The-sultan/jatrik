package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;



public interface IEMUsuarios {


	void delete(Usuario usuario);

	Usuario update(Usuario usuario);

	Usuario find(Long id);

	List<Usuario> findAll();

	Usuario findUsuarioByNick(String nick);
	
	Usuario add(Usuario usuario);
}