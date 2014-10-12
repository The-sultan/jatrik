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
import uy.edu.fing.tsi2.jatrik.core.utils.DateUtils;
import uy.edu.fing.tsi2.jatrik.core.utils.RandomUtil;



@Stateless
@Local(EJBManagerSimuladorBeanLocal.class)
@Remote(EJBManagerSimuladorBeanRemote.class)
public class EJBManagerSimuladorBean implements ISimulacion {

	private Logger logger = Logger.getLogger(EJBManagerSimuladorBean.class);
	
	static final private int DURACION_PARTIDO = 30;
	
	private Map<Long, Integer> cronometros;
	
	@EJB
	EJBManagerPartidoLocal partidosManager;
	
	public EJBManagerSimuladorBean() {
		super();
		cronometros = new HashMap<Long, Integer>();
	}

	public void simularEvento() {
		
		logger.info("############ ARRANCA Simular Evento #############");
	   
		// Me traigo solo los partidos Pendientes y En_Curso
		Set<EnumEstadoPartido> estados = new HashSet<EnumEstadoPartido>();
		estados.add(EnumEstadoPartido.PENDIENTE);
		estados.add(EnumEstadoPartido.EN_CURSO);
	
		Date fechaDesde = DateUtils.getDateWithoutTime(new Date());
		Date fechaHasta = new Date();
		
		List<Partido> partidos = partidosManager.obtenerPartidos(fechaDesde, fechaHasta, estados);
		for (Partido partido : partidos) {
			if (partido.getEstado() == EnumEstadoPartido.PENDIENTE) {
				// Esta pendiente - se da comienzo al mismo
				partido.setEstado(EnumEstadoPartido.EN_CURSO);
				//TODO Ver como seguimos
				cronometros.put(partido.getId(), new Integer(0));
				logger.info("##### Se inicio el partido: " + partido.getId());
				
			}else{
				int minuto = this.cronometros.get(partido.getId()).intValue();
				minuto++; // Aumento el minuto
				if (minuto <= DURACION_PARTIDO) {
					//TODO DEbo sortear el evento 
					Long id = realizarSorteoDeEvento();
					// Obtengo el id del manejador en la base
					//Me traigo el evento de la base
					Evento evento = partidosManager.encontrarEvento(id);
					logger.info("Partido :" + partido.getId() + " Minuto: " + minuto);

					manejarEvento(partido, minuto, evento);
					
					cronometros.put(partido.getId(), new Integer(minuto));
				}else{
					// TODO Se elimina el cronometro del partido y se finaliza el  mismo Actualizar
					cronometros.remove(partido.getId());
					partido.setEstado(EnumEstadoPartido.FINALIZADO);
				}
				
			}
		}
				
		logger.info("############ FIN Simular Evento #############");
	}
	
	private Long realizarSorteoDeEvento() {

		int random = RandomUtil.random(100);
		
		return (long) 1;
		
		
	}
	
	@SuppressWarnings("unused")
	private void manejarEvento(Partido partido, int minuto, Evento evento) {
		try {
			Class manejadorClass = Class.forName(evento.getManejador());

			ManejarEvento manejador = (ManejarEvento) manejadorClass.newInstance();
			manejador.manejarEvento(partidosManager, partido, minuto, evento);

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
