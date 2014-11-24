package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.Random;
import java.util.logging.Level;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.ejb.ISimulacion;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerPartidoLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerSimuladorBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerUsuarioLocal;
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
	EJBManagerUsuarioLocal usuariosManager;		
	
	@EJB
	EJBEMEquiposLocal equiposEJB;
	
	@EJB
	EJBEMPartidosLocal partidosEJB;
	
	@EJB
	ContextoSimulacion contextoSimulacion;
	
	public EJBManagerSimuladorBean() {
		super();
	}

	
	private void simularEvento(Long idPartido) {
		Partido partido = partidosEJB.find(idPartido);
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
	
	@Override
	@Asynchronous
	public void simularPartido(Long idPartido){
		Partido partido = partidosEJB.find(idPartido);
		Long idUsuarioLocal = partido.getLocal().getUsuario() == null ? null :partido.getLocal().getUsuario().getId();
		Long idUsuarioVisitante = partido.getVisitante().getUsuario() == null ? null :partido.getVisitante().getUsuario().getId();
		usuariosManager.EnviarMensajePush(idUsuarioLocal, "El partido " + partido.getLocal().getNombre() + " vs " + partido.getVisitante().getNombre() + " está comenzando!");
		usuariosManager.EnviarMensajePush(idUsuarioVisitante, "El partido " + partido.getLocal().getNombre() + " vs " + partido.getVisitante().getNombre() + " está comenzando!");
		if(partido.getEstado() == EnumEstadoPartido.PENDIENTE){
			partidosEJB.inicializarPartido(partido);
			for(int i=0; i<=90;i++){
				try {
					Thread.sleep(1000);
					simularEvento(partido.getId());
				} catch (InterruptedException ex) {
					java.util.logging.Logger.getLogger(EJBManagerSimuladorBean.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		usuariosManager.EnviarMensajePush(idUsuarioLocal, "Ha finalizado el partido: " + partido.getLocal().getNombre() + " " + partido.getGolesLocal() + " - " + partido.getGolesVisitante() + " " + partido.getVisitante().getNombre());
		usuariosManager.EnviarMensajePush(idUsuarioVisitante, "Ha finalizado el partido: " + partido.getLocal().getNombre() + " " + partido.getGolesLocal() + " - " + partido.getGolesVisitante() + " " + partido.getVisitante().getNombre());
		
	}
	
	@SuppressWarnings("unused")
	private void ejecutarEstrategia(Partido partido) {
		int numeroSorteado = randomGenerator.nextInt(contextoSimulacion.getPesoTotal());
		contextoSimulacion.ejecutarEstrategia(numeroSorteado, partido);

	}
}
