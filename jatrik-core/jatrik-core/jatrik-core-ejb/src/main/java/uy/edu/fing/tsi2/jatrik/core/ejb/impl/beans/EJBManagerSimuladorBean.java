package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Evento;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.ejb.ISimulacion;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerPartidoLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerSimuladorBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerSimuladorBeanRemote;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEstadoPartido;
import uy.edu.fing.tsi2.jatrik.core.eventos.ManejarEvento;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMPartidosLocal;
import uy.edu.fing.tsi2.jatrik.core.utils.DateUtils;
import uy.edu.fing.tsi2.jatrik.core.utils.RandomUtil;



@Stateless
@Local(EJBManagerSimuladorBeanLocal.class)
@Remote(EJBManagerSimuladorBeanRemote.class)
public class EJBManagerSimuladorBean implements ISimulacion {

	private Logger logger = Logger.getLogger(EJBManagerSimuladorBean.class);
	
	static final private int DURACION_PARTIDO = 90;
	
	@EJB
	EJBManagerPartidoLocal partidosManager;
	
	@EJB
	EJBEMEquiposLocal equiposEJB;
	
	@EJB
	EJBEMPartidosLocal partidosEJB;
	
	public EJBManagerSimuladorBean() {
		super();
	}

	
	public void simularEvento(Partido partido) {
		
		logger.info("############ ARRANCA Simular Evento #############");
   
		partido.setMinuto(partido.getMinuto()+1);
		if (partido.getMinuto() <= DURACION_PARTIDO) {
			//TODO DEbo sortear el evento 
			Long id = realizarSorteoDeEvento();
			// Obtengo el id del manejador en la base
			//Me traigo el evento de la base
			Evento evento = partidosManager.encontrarEvento(id);
			//logger.info("Partido :" + partido.getId() + " Minuto: " + minuto);
			manejarEvento(partido, evento);
		}else{
			// TODO Se elimina el cronometro del partido y se finaliza el  mismo Actualizar
			partido.setEstado(EnumEstadoPartido.FINALIZADO);
		}
	
	logger.info("############ FIN Simular Evento #############");
		
	}
	
	private Long realizarSorteoDeEvento() {

		int random = RandomUtil.random(100);
		
		if(random < 4){
			//retorno id de manejador lesion
			return 2L;
		}
		else if(random < 25){
			//retorno id de manejador de falta
			return 4L;
		}
		else if(random < 65){
			//evento idle, ahora tira jugada de gol pero hay que cambiarlo
			return 3L;
		}
		else{
			//evento posible jugada de gol
			return 3L;
		}
	}
	
	public void simularPartido(Long idPartido){
		Partido partido = partidosEJB.find(idPartido);
		partido.setMinuto(0);
		for(int i=0; i<90;i++){
			simularEvento(partido);
		}
		
	}
	
	@SuppressWarnings("unused")
	private void manejarEvento(Partido partido, Evento evento) {
		try {
			Class manejadorClass = Class.forName(evento.getManejador());

			ManejarEvento manejador = (ManejarEvento) manejadorClass.newInstance();
			manejador.manejarEvento(partidosManager, partido, evento);

		} catch (ClassNotFoundException e) {
			logger.info("***** Manejador no implementado *****");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
