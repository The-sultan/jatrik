package uy.edu.fing.tsi2.jatrik.core.ejb;

import java.util.List;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;

public interface IPartidos {

	List<Partido> obtenerPartidos(Long idEquipo);
}
