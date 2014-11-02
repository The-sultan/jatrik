package uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias;

import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.jatrik.core.domain.Evento;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.domain.eventos.EventoPartidoHito;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEventos;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.bean.EJBEMEquiposBean;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEventosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEventosPartidosLocal;

@Stateless
public class EstrategiaHito extends EstrategiaSimulacion{
	
	@EJB
	EJBEMEventosLocal eventosEJB;
	
	@EJB
	EJBEMEventosPartidosLocal eventosPartidosEJB;
	
	@EJB
	EJBEMEquiposBean equiposEJB;
	
	public EstrategiaHito(){
		this.setPeso(0);
	}
	
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
			Random random = new Random();
			Integer entradasVendidas = random.nextInt(30000);
			entradasVendidas += 5000; //el random va de 5000 a 35000
			Double fondos = partido.getLocal().getFondos();
			fondos += entradasVendidas * 200;
			partido.getLocal().setFondos(fondos);
			equiposEJB.update(partido.getLocal());
			tipoEvento = EnumEventos.FIN_PARTIDO;
		}
		Evento evento = eventosEJB.findByName(tipoEvento.name());
		EventoPartidoHito eventoPartido = new EventoPartidoHito(partido.getMinuto(), partido, evento);
		eventosPartidosEJB.add(eventoPartido);
	}
	
	
	

}
