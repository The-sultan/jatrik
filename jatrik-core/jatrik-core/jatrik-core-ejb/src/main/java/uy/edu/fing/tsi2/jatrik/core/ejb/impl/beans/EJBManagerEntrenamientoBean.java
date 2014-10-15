package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.List;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.jatrik.core.domain.Habilidad;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.ejb.IEntrenamiento;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEntrenamientoLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerEntrenamientoRemote;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumHabilidad;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.utils.DateUtils;

@Stateless
@Local(EJBManagerEntrenamientoLocal.class)
@Remote(EJBManagerEntrenamientoRemote.class)
public class EJBManagerEntrenamientoBean implements IEntrenamiento{

	@EJB
	EJBEMJugadoresLocal jugadoresEJB;

	@Override
	public String entrenarEquipo(Long idEquipo, Date fechaEntrenamiento, EnumHabilidad modoEntrenamiento) {
		if (modoEntrenamiento == null){
			return ("Modo de Entrenamiento invalido");
		}
		else {
			Set<Jugador> jugadores = jugadoresEJB.findJugadoresdelEquipo(idEquipo);
			// Primera recorrida para saber si ya entrene ese modo en el dia
			boolean yaEntrene = false;
			for(Jugador j : jugadores){
				List<Habilidad> habilidades = j.getHabilidades();
				for(Habilidad h : habilidades){
					Date fecha1 = DateUtils.getDateWithoutTime(fechaEntrenamiento);
					Date fecha2 = DateUtils.getDateWithoutTime(h.getUltimoEntrenamiento());
					if ((modoEntrenamiento.compareTo(h.getTipo()) == 0) && (fecha1.compareTo(fecha2) <= 0)){
						yaEntrene = true;
						break;
					}
				}
				if (yaEntrene)
					break;
				
			}
			// Si no entrene subo las habilidades
			if (yaEntrene)
				return ("El equipo ya entreno la habilidad " + modoEntrenamiento.getHabilidad() + " el dia de hoy.");
			else{
				for(Jugador j : jugadores){
					List<Habilidad> habilidades = j.getHabilidades();
					for(Habilidad h : habilidades){
						if (modoEntrenamiento.compareTo(h.getTipo()) == 0){
							Random r = new Random();
							int Valor = Math.round((100 - h.getValor()) / (h.getValor()+1));
							if (Valor >= 10){
								Valor = r.nextInt(5)+5;
							}
							h.setValor(h.getValor() + Valor);
							h.setUltimoEntrenamiento(new Date());
						}
					}
					jugadoresEJB.update(j);
				}
				return ("Has entrenado la habilidad " + modoEntrenamiento.getHabilidad() + " .");
			}			
		}
		
	}
	
}
