package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEstadio;

import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Liga;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.domain.RelLigaEquipo;
import uy.edu.fing.tsi2.jatrik.core.domain.RelLigaPartido;
import uy.edu.fing.tsi2.jatrik.core.ejb.ILigas;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEquipoBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerLigaBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerLigaBeanRemote;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumEstadoPartido;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMLigasLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMPartidosLocal;

@Stateless
@Local(EJBManagerLigaBeanLocal.class)
@Remote(EJBManagerLigaBeanRemote.class)
public class EJBManagerLigaBean implements ILigas {

	private Logger logger = Logger.getLogger(EJBManagerLigaBean.class);

	private static final long TIEMPO_ENTRE_PARTIDOS = 2 * 60 * 1000; // 2 minutos = 2 * 60 * 1000
	private static final long UN_MINUTO = 1 * 60 * 1000; // 2 minutos = 2 * 60 * 1000
	private static final double fondos= 5000;
        private static final double latitudEstadio=-34.8580556;
        private static final double longitudEstadio=-56.1708333;
        private static final int alturaEstadio=2000;
        
	@EJB
	EJBEMLigasLocal ligasEJB;
	
	@EJB
	EJBEMEquiposLocal equiposEJB;
	
	@EJB
	EJBManagerEquipoBeanLocal equipoEJBManager;
	
	@EJB
	EJBEMPartidosLocal partidosEJB;
	
	List<String> nombreEquipos;
	
	public static final int CANT_EQUIPOS_LIGA = 16;
	
	public void initLigas(){
		List<Liga> ligas = ligasEJB.findAll();
		if(!ligas.isEmpty()){
			return;
		}
		List<String> nombreEquipos = new ArrayList<>();
		nombreEquipos.add("Cerro");
		nombreEquipos.add("Wanderers");
		nombreEquipos.add("Danubio");
		nombreEquipos.add("River");
		nombreEquipos.add("Defensor");
		nombreEquipos.add("Peñarol");
		nombreEquipos.add("Naciomal");
		nombreEquipos.add("Rampla");
		nombreEquipos.add("El Tanque Sisley");
		nombreEquipos.add("Racing");
		nombreEquipos.add("Fenix");
		nombreEquipos.add("Atenas");
		nombreEquipos.add("Sud America");
		nombreEquipos.add("Tacuarembo");
		nombreEquipos.add("Rampla Juniors");
		nombreEquipos.add("Juventud");
		
		for(String nombreEquipo : nombreEquipos){
			InfoEquipo infoEquipo = new InfoEquipo();
			infoEquipo.setNombre(nombreEquipo);
			InfoEstadio infoEstadio = new InfoEstadio();
			infoEstadio.setNombre("Estadio1");
			infoEstadio.setAltura(alturaEstadio);
                        infoEstadio.setLatitud(latitudEstadio);
                        infoEstadio.setLongitud(longitudEstadio);
                        infoEquipo.setEstadio(infoEstadio);
                        infoEquipo.setFondos(fondos);                       
			equipoEJBManager.crearEquipo(infoEquipo, null);
		}
		creandoMiLiga("Campeonato Uruguayo de Futbol");
			
	}
	
	public Long creandoMiLiga(String nombre){
		logger.info("###### VAMOS A CREAR LA LIGA ######");
		Liga miLiga = new Liga();
		miLiga.setDescripcion(nombre);
		
		ligasEJB.add(miLiga);		
		
		List<Equipo> teams = equiposEJB.findAll();
		
		for (Equipo equipo : teams) {
			inscribirEquipoEnLiga(equipo, miLiga);
		}
		
		crearFixture(miLiga);
		logger.info("###### FIN VAMOS A CREAR LA LIGA ######");
		return miLiga.getId();
		
	}
	
	public void inscribirEquipoEnLiga(Equipo equipo, Liga liga) {
		Set<RelLigaEquipo> tablaPosiciones = liga.getRelLigaEquipo();
		tablaPosiciones.add(new RelLigaEquipo(liga, equipo));
		ligasEJB.update(liga);
	}
	
	
	public void crearLiga(String descripcion) {
		Liga liga = new Liga(descripcion, null, null);
		ligasEJB.add(liga);
	}
	
	
	
	public void crearFixture(Liga liga) {
		
		logger.info("#### CREANDO FIXTURE ####");
		
			
		List<Equipo> equipos = new ArrayList<Equipo>();

		Set<RelLigaEquipo> tabla = liga.getRelLigaEquipo();
		
		for (RelLigaEquipo relLigaEquipo : tabla) {
			Equipo equipo = relLigaEquipo.getEquipo();
			equipos.add(equipo);
		}

		int cantEquipos = equipos.size();
		if (cantEquipos > 1) {
			List<Partido> partidosDeIda = new ArrayList<Partido>();
			List<Partido> partidosDeVuelta = new ArrayList<Partido>();

			int i = 0;
			long primerFechaIda = (new Date()).getTime() + UN_MINUTO;// +
																		// TIEMPO_ENTRE_PARTIDOS;
			long primerFechaVuelta = primerFechaIda
					+ (TIEMPO_ENTRE_PARTIDOS * factorial(cantEquipos - 1))
					+ UN_MINUTO;
			Date fechaInicio = new Date(primerFechaIda);
			Date fechaFin = null;

			for (int j = 0; j < cantEquipos; j++) {
				Equipo equipo1 = equipos.get(j);
				for (int k = j + 1; k < cantEquipos; k++, i++) {
					Equipo equipo2 = equipos.get(k);

					Partido partidoIda = new Partido(new Date(primerFechaIda
							+ (TIEMPO_ENTRE_PARTIDOS * i)),
							EnumEstadoPartido.PENDIENTE, equipo1, equipo2, 0, 0);
					partidosDeIda.add(partidoIda);
					Partido partidoVuelta = new Partido(new Date(
							primerFechaVuelta + (TIEMPO_ENTRE_PARTIDOS * i)),
							EnumEstadoPartido.PENDIENTE, equipo2, equipo1, 0, 0);
					partidosDeVuelta.add(partidoVuelta);

					// Me guardo la fecha del último partido para setearla en la
					//liga .
					fechaFin = new Date(primerFechaVuelta
							+ (TIEMPO_ENTRE_PARTIDOS * i));
				}
			}

			Set<RelLigaPartido> fixture = new HashSet<RelLigaPartido>();

			for (Partido partido : partidosDeIda) {
				logger.info("Partido    ida: " + partido.getLocal().getNombre()
						+ "-" + partido.getVisitante().getNombre() + " fecha: "
						+ partido.getFechaInicio());
				partidosEJB.add(partido);
				fixture.add(new RelLigaPartido(liga, partido));

			}
			for (Partido partido : partidosDeVuelta) {
				logger.info("Partido vuelta: " + partido.getLocal().getNombre()
						+ "-" + partido.getVisitante().getNombre() + " fecha: "
						+ partido.getFechaInicio());
				partidosEJB.add(partido);
				fixture.add(new RelLigaPartido(liga, partido));
			}

			liga.setRelLigaPartido(fixture);  
			liga.setFechaInicio(fechaInicio);
			liga.setFechaFin(fechaFin);

			ligasEJB.add(liga);

		}
		
		logger.info("#### FIN CREAR FIXTURE ####");
		
	}

	private int factorial(int n) {
		if (n <= 1) {
			return n;
		} else {
			return (n * factorial(n - 1));
		}
	}
}
