package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.jatrik.core.ejb.IEquipos;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEquipoBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerEquipoBeanRemote;

@Stateless
@Local(EJBManagerEquipoBeanLocal.class)
@Remote(EJBManagerEquipoBeanRemote.class)
public class EJBManagerEquipoBean implements IEquipos {
}
