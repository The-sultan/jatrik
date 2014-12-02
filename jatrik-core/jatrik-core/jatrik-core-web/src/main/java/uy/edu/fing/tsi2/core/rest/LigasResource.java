package uy.edu.fing.tsi2.core.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import uy.edu.fing.tsi2.jatrik.common.payloads.EquipoPosicionLiga;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoLiga;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoPartido;
import uy.edu.fing.tsi2.jatrik.core.domain.Liga;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.domain.RelLigaEquipo;
import uy.edu.fing.tsi2.jatrik.core.domain.RelLigaPartido;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerEquipoBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerLigaBeanLocal;
import uy.edu.fing.tsi2.jatrik.core.utils.DateUtils;

@RequestScoped
public class LigasResource {

    @EJB
    private EJBManagerLigaBeanLocal ligaEJB;

    @EJB
    private EJBManagerEquipoBeanLocal equipoEJB;

    @POST
    public Response crearLiga(InfoLiga liga) {
        Long ligaId = ligaEJB.creandoMiLiga(liga.getDescripcion());
        URI uri = null;
        try {
            uri = new URI("ligas/" + ligaId);

        } catch (URISyntaxException ex) {
            Logger.getLogger(LigasResource.class.getName()).log(Level.SEVERE,
                    null, ex);
            return Response.serverError().build();
        }
        return Response.created(uri).build();
    }

    @Path("/equipo/{id}")
    @Produces("application/json")
    @GET
    public Response obtenerInfoLiga(@PathParam("id") Long idEquipo) {

        InfoLiga resultado = new InfoLiga();
        try {
            Liga liga = ligaEJB.obtenerLigaEquipo(idEquipo);

            resultado.setDescripcion(liga.getDescripcion());
            resultado.setId(liga.getId());
            resultado.setFechaInicio(liga.getFechaInicio());
            resultado.setFechaFin(liga.getFechaFin());
            resultado.setDescripcion(liga.getDescripcion());

            Map<Integer, List<InfoPartido>> partidos = new HashMap<>();

            Set<RelLigaPartido> fixture = liga.getRelLigaPartido();

            for (RelLigaPartido relLigaPartido : fixture) {
                Integer etapa = relLigaPartido.getPartido().getEtapa();
                Partido partido = relLigaPartido.getPartido();
                InfoPartido infoPartido = new InfoPartido(partido.getId(), 
                                                          partido.getEstado().name(), 
                                                          partido.getLocal().getNombre(),
                                                          partido.getVisitante().getNombre(), 
                                                          partido.getGolesLocal(), 
                                                          partido.getGolesVisitante());
                
                infoPartido.setFecha(DateUtils.getFecha(partido.getFechaInicio()));
                
                if (partidos.containsKey(etapa)) {
                    List<InfoPartido> listInfoPartido = partidos.get(etapa);
                    listInfoPartido.add(infoPartido);
                } else {
                    List<InfoPartido> listInfoPartido = new ArrayList<>();
                    listInfoPartido.add(infoPartido);
                    partidos.put(etapa, listInfoPartido);
                }

            }
            Set<RelLigaEquipo> tablaPosicion = liga.getRelLigaEquipo();
            List<EquipoPosicionLiga> posiciones = new ArrayList<EquipoPosicionLiga>();
            for (RelLigaEquipo tabla : tablaPosicion) {
                EquipoPosicionLiga pos = new EquipoPosicionLiga();
                pos.setNombreEquipo(tabla.getEquipo().getNombre());
                pos.setPtos(tabla.getPtos());
                pos.setDiferencia(tabla.getDiferencia());
                pos.setGolesAFavor(tabla.getGolesAFavor());
                pos.setGolesEnContra(tabla.getGolesEnContra());
                pos.setPartidosJugados(tabla.getPartidosJugados());
                pos.setPartidosGanados(tabla.getPartidosGanados());
                pos.setPartidosPerdidos(tabla.getPartidosPerdidos());
                pos.setPartidosEmpatados(tabla.getPartidosEmpatados());
                posiciones.add(pos);
            }
            
            Collections.sort(posiciones,new Comparator<EquipoPosicionLiga>() {
            	@Override
            	public int compare(EquipoPosicionLiga arg0,
            			EquipoPosicionLiga arg1) {
            		
            		if (arg1.getPtos() == arg0.getPtos()){
            			return (new Integer(arg1.getGolesAFavor()-arg1.getGolesEnContra())).compareTo(new Integer(arg0.getGolesAFavor()-arg0.getGolesEnContra()));
            		}
            			
            			
            		
            		return (new Integer(arg1.getPtos())).compareTo(arg0.getPtos());
            	}
			});            
            
            resultado.setPartidos(partidos);
            resultado.setPosisiones(posiciones);
            return Response.ok(resultado).build();
        } catch (Exception e) {
            Logger.getLogger(LigasResource.class.getName()).log(Level.SEVERE, null, e);
        }
        return Response.serverError().build();
    }
    
}
