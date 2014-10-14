package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.Date;
import java.util.List;
import java.util.Set;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEstadoPartido;

public interface IEMPartidos {

	Partido add(Partido partido);

	Partido update(Partido partido);

	void delete(Partido partido);

	Partido find(Long id);

	List<Partido> findAll();
	
	List<Partido> findPartidosDeEquipo(Long idEquipo);
	
	List<Partido> findPartidosPorFechayEstados(Date fechaDesde, Date fechaHasta,Set<EnumEstadoPartido> estados);
	
	void inicializarPartido(Partido partido);
	
}
	
