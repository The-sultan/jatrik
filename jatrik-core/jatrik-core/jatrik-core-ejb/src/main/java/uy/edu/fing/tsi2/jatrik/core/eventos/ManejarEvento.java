package uy.edu.fing.tsi2.jatrik.core.eventos;

import javax.ejb.Local;

import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.ejb.IPartidos;


@Local
public abstract class ManejarEvento {
	public abstract void manejarEvento(IPartidos partidosManager,
			Partido partido, int minuto, Evento evento);

}
