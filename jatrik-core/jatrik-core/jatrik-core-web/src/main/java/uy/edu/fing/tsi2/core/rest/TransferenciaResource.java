package uy.edu.fing.tsi2.core.rest;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
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

import org.apache.commons.beanutils.BeanUtils;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoHabilidad;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferencia;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferenciaCompra;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferenciaVenta;
import uy.edu.fing.tsi2.jatrik.core.domain.Habilidad;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Transferencia;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerTransferenciaBeanLocal;

@RequestScoped
public class TransferenciaResource {

	@EJB
	private EJBManagerTransferenciaBeanLocal transferenciaEJB;

	@POST
	@Path("/vender")
	public Response ponerJugadorEnVenta(InfoTransferenciaVenta info) {
		boolean respuesta = transferenciaEJB.ponerJugadorEnVenta(info.getIdJugador(), info.getIdEquipoVende(), info.getPrecio());
		URI uri = null;
		try {
			uri = new URI("transferencia/" + respuesta);
		} catch (URISyntaxException ex) {
			Logger.getLogger(TransferenciaResource.class.getName()).log(Level.SEVERE, null, ex);
			return Response.serverError().build();
		}
		return Response.created(uri).build();
	}

	@POST
	@Path("/comprar")
	public Response comprarJugadorEnVenta(InfoTransferenciaCompra info) {
		boolean respuesta = this.transferenciaEJB.comprarJugador(info.getIdTransferencia(), info.getIdEquipoCompra());
		URI uri = null;
		try {
			uri = new URI("transferencia/" + respuesta);
		} catch (URISyntaxException ex) {
			Logger.getLogger(TransferenciaResource.class.getName()).log(Level.SEVERE, null, ex);
			return Response.serverError().build();
		}
		return Response.created(uri).build();
	}

	@GET
	@Produces({ "application/json" })
	public Response listadoDeJuagadoresEnVenta() {
		List<InfoTransferencia> resultado = new ArrayList<InfoTransferencia>();
		try {
			List<Transferencia> transferencias = transferenciaEJB.listadoJugadoresEnVenta();
			for (Transferencia transferencia : transferencias) {
				InfoTransferencia infoTrans = new InfoTransferencia();
				infoTrans.setEquipoIdVendedor(transferencia.getVendedor().getId());
				infoTrans.setPrecio(transferencia.getPrecio());
				infoTrans.setId(transferencia.getId());
				InfoJugador infoJugador = getDtoFromEntity(transferencia.getJugador());
				infoTrans.setJugador(infoJugador);
				resultado.add(infoTrans);
			}
			return Response.ok(resultado).build();
		} catch (Exception ex) {
			Logger.getLogger(TransferenciaResource.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Response.serverError().build();
	}

	@Path("/{id}")
	@Produces("application/json")
	@GET
	public Response obtenerTrasferenciasDeOtros(@PathParam("id") Long id){
		List<InfoTransferencia> resultado = new ArrayList<InfoTransferencia>();
		try {
			List<Transferencia> transferencias = transferenciaEJB.listadoJugadoresEnVenta();
			for (Transferencia transferencia : transferencias) {
				if (!transferencia.getVendedor().getId().equals(id)){
					InfoTransferencia infoTrans = new InfoTransferencia();
					infoTrans.setEquipoIdVendedor(transferencia.getVendedor().getId());
					infoTrans.setPrecio(transferencia.getPrecio());
					infoTrans.setId(transferencia.getId());
					InfoJugador infoJugador = getDtoFromEntity(transferencia.getJugador());
					infoTrans.setJugador(infoJugador);
					resultado.add(infoTrans);
				}
			}
			return Response.ok(resultado).build();
		} catch (Exception ex) {
			Logger.getLogger(TransferenciaResource.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Response.serverError().build();
	}
	
	
	private InfoJugador getDtoFromEntity(Jugador jugador) {
		InfoJugador infoJugador = new InfoJugador();
		try {
			BeanUtils.copyProperties(infoJugador, jugador);
			List<InfoHabilidad> infoHabilidades = new ArrayList<InfoHabilidad>();
			for (Habilidad habilidad : jugador.getHabilidades()) {
				InfoHabilidad infoHabilidad = new InfoHabilidad();
				BeanUtils.copyProperties(infoHabilidad, habilidad);
				infoHabilidades.add(infoHabilidad);
				infoHabilidad.setNombre(habilidad.getTipo().getHabilidad());
			}
			infoJugador.setHabilidades(infoHabilidades);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(TransferenciaResource.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(TransferenciaResource.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return infoJugador;
	}
}