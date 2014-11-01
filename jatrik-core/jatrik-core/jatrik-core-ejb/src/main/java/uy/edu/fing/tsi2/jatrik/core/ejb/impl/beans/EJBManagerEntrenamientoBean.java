package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.List;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Habilidad;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.ejb.IEntrenamiento;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEntrenamientoLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerEntrenamientoRemote;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumHabilidad;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.utils.DateUtils;

@Stateless
@Local(EJBManagerEntrenamientoLocal.class)
@Remote(EJBManagerEntrenamientoRemote.class)
public class EJBManagerEntrenamientoBean implements IEntrenamiento{

	@EJB
	EJBEMJugadoresLocal jugadoresEJB;

	@EJB
	EJBEMEquiposLocal equiposEJB;	
	
	@Override
	public void entrenarJugador(Long idJugador, EnumHabilidad modoEntrenamiento) {
		Jugador j = jugadoresEJB.find(idJugador);
		if (modoEntrenamiento != null){
			List<Habilidad> habilidades = j.getHabilidades();
			for(Habilidad h : habilidades){
				if (modoEntrenamiento.compareTo(h.getTipo()) == 0){
					Random r = new Random();
					int Valor = Math.round((100 - h.getValor()) / (h.getValor()+1));
					if (Valor >= 10){
						Valor = r.nextInt(5)+5;
					}
					h.setValor(h.getValor() + Valor);
				}
			}
			jugadoresEJB.update(j);	
		}
	}

	@Override
	public boolean puedeEntrenar(Long idEquipo, Date fechaEntrenamiento) {
		boolean yaEntrene = false;
		Equipo e = equiposEJB.find(idEquipo);
		Date fecha1 = DateUtils.getDateWithoutTime(fechaEntrenamiento);
		Date fecha2 = DateUtils.getDateWithoutTime(e.getUltimoEntrenamiento());
		if (fecha1.compareTo(fecha2) <= 0){
			yaEntrene = true;
			e.setUltimoEntrenamiento(fechaEntrenamiento);
		}
		return yaEntrene;
	}
	
}
