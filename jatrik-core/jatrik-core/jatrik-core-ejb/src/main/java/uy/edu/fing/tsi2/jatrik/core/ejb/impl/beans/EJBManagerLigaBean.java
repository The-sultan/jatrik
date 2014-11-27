package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private static final double fondos = 5000;
    private static final double latitudEstadio = -34.8580556;
    private static final double longitudEstadio = -56.1708333;
    private static final int alturaEstadio = 2000;

    @EJB
    EJBEMLigasLocal ligasEJB;

    @EJB
    EJBEMEquiposLocal equiposEJB;

    @EJB
    EJBManagerEquipoBeanLocal equipoEJBManager;

    @EJB
    EJBEMPartidosLocal partidosEJB;

    List<String> nombreEquipos;
    // OJO que la cantidad de equipos en la liga tiene que ser PaR
    public static final int CANT_EQUIPOS_LIGA = 16;

    public void initLigas() {
        List<Liga> ligas = ligasEJB.findAll();
        if (!ligas.isEmpty()) {
            return;
        }

        List<String> nombreEquipos = new ArrayList<>();
        nombreEquipos.add("Cerro");
        nombreEquipos.add("Wanderers");
        nombreEquipos.add("Danubio");
        nombreEquipos.add("River");
        /*nombreEquipos.add("Defensor");
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
        nombreEquipos.add("Juventud");*/

        for (String nombreEquipo : nombreEquipos) {
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

    public Long creandoMiLiga(String nombre) {
        logger.info("###### VAMOS A CREAR LA LIGA ######");
        Liga miLiga = new Liga();
        miLiga.setDescripcion(nombre);

        ligasEJB.add(miLiga);

        List<Equipo> teams = equiposEJB.findAll();

        for (Equipo equipo : teams) {
            inscribirEquipoEnLiga(equipo, miLiga);
        }

        crearFixtureConEtapas(miLiga);
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

    public void crearFixtureConEtapas(Liga liga) {

        logger.info("#### CREANDO FIXTURE CON ETAPAS####");

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

            long primerFechaIda = (new Date()).getTime() + UN_MINUTO;// +
            // TIEMPO_ENTRE_PARTIDOS;
            long primerFechaVuelta = primerFechaIda
                    + (TIEMPO_ENTRE_PARTIDOS * cantEquipos/2 * cantEquipos-1)/*factorial(cantEquipos - 1))*/
                    + UN_MINUTO;
            
            Date fechaInicio = new Date(primerFechaIda);
            Date fechaFin = null;

            // Ordena para mezclar
            List<Equipo> parte1 = new ArrayList<Equipo>();
            List<Equipo> parte2 = new ArrayList<Equipo>();
            for (int j = 0; j < cantEquipos / 2; j++) {
                parte1.add(j, equipos.get(j));
                parte2.add(j, equipos.get(cantEquipos - j - 1));

            }

            int etapas = cantEquipos - 1;
            for (int i = 1; i <= etapas; i++) {

                for (int j = 0; j < cantEquipos / 2; j++) {

                    Partido partidoIda = new Partido(new Date(primerFechaIda
                            + (TIEMPO_ENTRE_PARTIDOS * i)),
                            EnumEstadoPartido.PENDIENTE, parte1.get(j), parte2.get(j), 0, 0);

                    partidoIda.setEtapa(i);
                    partidosDeIda.add(partidoIda);

                    Partido partidoVuelta = new Partido(new Date(
                            primerFechaVuelta + (TIEMPO_ENTRE_PARTIDOS * i)),
                            EnumEstadoPartido.PENDIENTE, parte2.get(j), parte1.get(j), 0, 0);

                    partidoVuelta.setEtapa(i + etapas);
                    partidosDeVuelta.add(partidoVuelta);

                    // Me guardo la fecha del último partido para setearla en la
                    //liga .
                    fechaFin = new Date(primerFechaVuelta
                            + (TIEMPO_ENTRE_PARTIDOS * i));

                }
                Equipo aux2 = parte2.get(0);
                Equipo aux1 = parte1.get(parte1.size() - 1);
                parte1.remove(aux1);
                parte2.remove(aux2);
                parte1.add(1, aux2);
                parte2.add(parte2.size(), aux1);

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

//    public void crearFixture(Liga liga) {
//
//        logger.info("#### CREANDO FIXTURE ####");
//
//        List<Equipo> equipos = new ArrayList<Equipo>();
//
//        Set<RelLigaEquipo> tabla = liga.getRelLigaEquipo();
//
//        for (RelLigaEquipo relLigaEquipo : tabla) {
//            Equipo equipo = relLigaEquipo.getEquipo();
//            equipos.add(equipo);
//        }
//
//        int cantEquipos = equipos.size();
//        if (cantEquipos > 1) {
//            List<Partido> partidosDeIda = new ArrayList<Partido>();
//            List<Partido> partidosDeVuelta = new ArrayList<Partido>();
//
//            int i = 0;
//            long primerFechaIda = (new Date()).getTime() + UN_MINUTO;// +
//            // TIEMPO_ENTRE_PARTIDOS;
//            long primerFechaVuelta = primerFechaIda
//                    + (TIEMPO_ENTRE_PARTIDOS * factorial(cantEquipos - 1))
//                    + UN_MINUTO;
//            Date fechaInicio = new Date(primerFechaIda);
//            Date fechaFin = null;
//
//            for (int j = 0; j < cantEquipos; j++) {
//                Equipo equipo1 = equipos.get(j);
//                for (int k = j + 1; k < cantEquipos; k++, i++) {
//                    Equipo equipo2 = equipos.get(k);
//
//                    Partido partidoIda = new Partido(new Date(primerFechaIda
//                            + (TIEMPO_ENTRE_PARTIDOS * i)),
//                            EnumEstadoPartido.PENDIENTE, equipo1, equipo2, 0, 0);
//                    partidoIda.setEtapa(etapaAux);
//                    partidosDeIda.add(partidoIda);
//                    Partido partidoVuelta = new Partido(new Date(
//                            primerFechaVuelta + (TIEMPO_ENTRE_PARTIDOS * i)),
//                            EnumEstadoPartido.PENDIENTE, equipo2, equipo1, 0, 0);
//                    partidoVuelta.setEtapa(etapaAux + cantEquipos / 2);
//                    partidosDeVuelta.add(partidoVuelta);
//
//                    // Me guardo la fecha del último partido para setearla en la
//                    //liga .
//                    fechaFin = new Date(primerFechaVuelta
//                            + (TIEMPO_ENTRE_PARTIDOS * i));
//                    etapaAux++;
//                }
//                etapaAux = etapa++;
//            }
//
//            Set<RelLigaPartido> fixture = new HashSet<RelLigaPartido>();
//
//            for (Partido partido : partidosDeIda) {
//                logger.info("Partido    ida: " + partido.getLocal().getNombre()
//                        + "-" + partido.getVisitante().getNombre() + " fecha: "
//                        + partido.getFechaInicio());
//                partidosEJB.add(partido);
//                fixture.add(new RelLigaPartido(liga, partido));
//
//            }
//            for (Partido partido : partidosDeVuelta) {
//                logger.info("Partido vuelta: " + partido.getLocal().getNombre()
//                        + "-" + partido.getVisitante().getNombre() + " fecha: "
//                        + partido.getFechaInicio());
//                partidosEJB.add(partido);
//                fixture.add(new RelLigaPartido(liga, partido));
//            }
//
//            liga.setRelLigaPartido(fixture);
//            liga.setFechaInicio(fechaInicio);
//            liga.setFechaFin(fechaFin);
//
//            ligasEJB.add(liga);
//
//        }
//
//        logger.info("#### FIN CREAR FIXTURE ####");
//
//    }
    
    public void actualizarTablaPosiciones(Partido partido) {
        Liga liga = ligasEJB.findLigaByPartido(partido);
        Set<RelLigaEquipo> tabla = liga.getRelLigaEquipo();

        Equipo equipoGanador = null;
        Equipo equipoPerdedor = null;
        int golesGanador = 0;
        int golesPerdedor = 0;
        if (partido.getGolesLocal() > partido.getGolesVisitante()) {
            equipoGanador = partido.getLocal();
            equipoPerdedor = partido.getVisitante();
            golesGanador = partido.getGolesLocal();
            golesPerdedor = partido.getGolesVisitante();
        } else if (partido.getGolesLocal() < partido.getGolesVisitante()) {
            equipoGanador = partido.getVisitante();
            equipoPerdedor = partido.getLocal();
            golesGanador = partido.getGolesVisitante();
            golesPerdedor = partido.getGolesLocal();
        }

        for (RelLigaEquipo relLigaEquipo : tabla) {

            Equipo equipo = relLigaEquipo.getEquipo();
            
            if (equipo.equals(partido.getLocal()) || equipo.equals(partido.getVisitante())) {
                relLigaEquipo.setPartidosJugados(relLigaEquipo.getPartidosJugados() + 1);
                if (equipoGanador == null && equipoPerdedor == null) {
                         // Empate, sumo un pto a cada equipo.
                        // Actualizo GF y GC para cada equipo. Tomo uno de los dos equipos
                        // para conocer la cantidad de goles.
                        relLigaEquipo.setPtos(relLigaEquipo.getPtos() + 1);
                        relLigaEquipo.setGolesAFavor(relLigaEquipo.getGolesAFavor() + partido.getGolesLocal());
                        relLigaEquipo.setGolesEnContra(relLigaEquipo.getGolesEnContra() + partido.getGolesLocal());
                        relLigaEquipo.setDiferencia(relLigaEquipo.getGolesAFavor() - relLigaEquipo.getGolesEnContra());
                } else {
                    if (equipo.equals(equipoGanador)) {
                        relLigaEquipo.setPartidosGanados(relLigaEquipo.getPartidosGanados() + 1);
                        relLigaEquipo.setPtos(relLigaEquipo.getPtos() + 3);
                        relLigaEquipo.setGolesAFavor(relLigaEquipo.getGolesAFavor() + golesGanador);
                        relLigaEquipo.setGolesEnContra(relLigaEquipo.getGolesEnContra() + golesPerdedor);
                        relLigaEquipo.setDiferencia(relLigaEquipo.getGolesAFavor() - relLigaEquipo.getGolesEnContra());
                    } else {
                        relLigaEquipo.setPartidosPerdidos(relLigaEquipo.getPartidosPerdidos() + 1);
                        relLigaEquipo.setGolesAFavor(relLigaEquipo.getGolesAFavor() + golesPerdedor);
                        relLigaEquipo.setGolesEnContra(relLigaEquipo.getGolesEnContra() + golesGanador);
                        relLigaEquipo.setDiferencia(relLigaEquipo.getGolesAFavor() - relLigaEquipo.getGolesEnContra());
                    }
                }

            }
        }

        ligasEJB.update(liga);
    }

    private int factorial(int n) {
        if (n <= 1) {
            return n;
        } else {
            return (n * factorial(n - 1));
        }
    }

    public Liga find(Long id) {
        return ligasEJB.find(id);
    }
    
    public Liga obtenerLigaEquipo(Equipo equipo) {
	return ligasEJB.findLigaByEquipo(equipo);
    }
    
    public Liga obtenerLigaPartido(Partido partido){
        return ligasEJB.findLigaByPartido(partido);
    }
}
