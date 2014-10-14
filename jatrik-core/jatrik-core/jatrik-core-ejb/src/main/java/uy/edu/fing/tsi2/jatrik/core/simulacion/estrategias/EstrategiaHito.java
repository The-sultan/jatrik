package uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import uy.edu.fing.tsi2.jatrik.core.domain.Evento;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.domain.eventos.EventoPartidoHito;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEventos;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEventosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEventosPartidosLocal;

@Stateless
public class EstrategiaHito extends EstrategiaSimulacion{
	
	@EJB
	EJBEMEventosLocal eventosEJB;
	
	@EJB
	EJBEMEventosPartidosLocal eventosPartidosEJB;
	
	public EstrategiaHito(){
		this.setPeso(0);
	}
	
	private Logger log = Logger.getLogger(EstrategiaFinPartido.class);

	@Override
	public void manejarEvento(Partido partido) {

		EnumEventos tipoEvento = null;
		if(partido.getMinuto() == 1){
			tipoEvento = EnumEventos.INICIO_PARTIDO;
		}
		else if(partido.getMinuto() == 45){
			tipoEvento = EnumEventos.FIN_PRIMER_TIEMPO;
		}
		else if(partido.getMinuto() == 46){
			tipoEvento = EnumEventos.INICIO_SEGUNDO_TIEMPO;
		}
		else if(partido.getMinuto() == 90){
			tipoEvento = EnumEventos.FIN_PARTIDO;
		}
		Evento evento = eventosEJB.findByName(tipoEvento.name());
		EventoPartidoHito eventoPartido = new EventoPartidoHito(partido.getMinuto(), partido, evento);
		eventosPartidosEJB.add(eventoPartido);
	}
	
	
	

}
