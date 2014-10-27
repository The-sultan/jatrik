package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.Transferencia;



public interface IEMTransferencias {

	Transferencia add(Transferencia Transferencia);

	Transferencia update(Transferencia Transferencia);

	void delete(Transferencia Transferencia);

	Transferencia find(Long id);

	List<Transferencia> findAll();
	
	List<Transferencia> findTransferenciasLibres();
	
	List<Transferencia> findTransferenciasdelEquipo(Long idEquipo);
	
}
	
