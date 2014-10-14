package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.Comentario;
import uy.edu.fing.tsi2.jatrik.core.domain.Evento;



public interface IEMComentarios {


	void delete(Comentario comentario);

	Comentario update(Comentario comentario);

	Comentario find(Long id);

	List<Comentario> findAll();

	List<Comentario> findComentariosByEventoAndNivel(Evento evento, Long nivel);
	
	Comentario add(Comentario comentario);
}