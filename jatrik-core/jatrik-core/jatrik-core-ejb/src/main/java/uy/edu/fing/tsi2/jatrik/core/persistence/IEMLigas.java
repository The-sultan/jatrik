package uy.edu.fing.tsi2.jatrik.core.persistence;

import java.util.List;

import uy.edu.fing.tsi2.jatrik.core.domain.Liga;



public interface IEMLigas {


	void delete(Liga liga);

	Liga update(Liga liga);

	Liga find(Long id);

	List<Liga> findAll();

	Liga add(Liga liga);
	
		
}