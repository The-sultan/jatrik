package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;

import uy.edu.fing.tsi2.jatrik.core.ejb.IEquipos;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEquipoBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerEquipoBeanRemote;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;

@Stateless
@Local(EJBManagerEquipoBeanLocal.class)
@Remote(EJBManagerEquipoBeanRemote.class)
public class EJBManagerEquipoBean implements IEquipos {
	
	@EJB
	EJBEMEquiposLocal equiposEJB;
	
	public Equipo find(Long id){
		return equiposEJB.find(id);
	}
}
