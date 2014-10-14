package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.ejb.ISimulacion;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerPartidoLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerSimuladorBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerSimuladorBeanRemote;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEstadoPartido;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMPartidosLocal;
import uy.edu.fing.tsi2.jatrik.core.simulacion.ContextoSimulacion;

/**
 *
 * @author Farid
 */
@Stateless
@Local(EJBManagerSimuladorBeanLocal.class)
@Remote(EJBManagerSimuladorBeanRemote.class)
public class EJBManagerSimuladorBean implements ISimulacion {

	private Logger logger = Logger.getLogger(EJBManagerSimuladorBean.class);
	
	static final private int DURACION_PARTIDO = 90;
	
	private Random randomGenerator = new Random();
	
	@EJB
	EJBManagerPartidoLocal partidosManager;
	
	@EJB
	EJBEMEquiposLocal equiposEJB;
	
	@EJB
	EJBEMPartidosLocal partidosEJB;
	
	@EJB
	ContextoSimulacion contextoSimulacion;
	
	public EJBManagerSimuladorBean() {
		super();
	}

	
	private void simularEvento(Partido partido) {
		
		partido.setMinuto(partido.getMinuto()+1);
		if (partido.getMinuto() <= DURACION_PARTIDO) {
			//TODO DEbo sortear el evento 
			//int random = realizarSorteoDeEvento();
			// Obtengo el id del manejador en la base
			//Me traigo el evento de la base
			//Evento evento = partidosManager.encontrarEvento(id);
			//logger.info("Partido :" + partido.getId() + " Minuto: " + minuto);
			ejecutarEstrategia(partido);
		}else{
			// TODO Se elimina el cronometro del partido y se finaliza el  mismo Actualizar
			partido.setEstado(EnumEstadoPartido.FINALIZADO);
		}
	
	}

	public void simularPartido(Long idPartido){
		Partido partido = partidosEJB.find(idPartido);
		partidosEJB.inicializarPartido(partido);
		for(int i=0; i<=90;i++){
			simularEvento(partido);
		}
		
	}
	
	@SuppressWarnings("unused")
	private void ejecutarEstrategia(Partido partido) {
		int numeroSorteado = randomGenerator.nextInt(contextoSimulacion.getPesoTotal());
		contextoSimulacion.ejecutarEstrategia(numeroSorteado, partido);

	}
}
