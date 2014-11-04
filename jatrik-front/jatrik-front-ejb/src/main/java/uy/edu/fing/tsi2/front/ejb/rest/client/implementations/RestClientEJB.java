package uy.edu.fing.tsi2.front.ejb.rest.client.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.front.ejb.rest.client.exceptions.RestClientException;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestRequestBuilderFactoryLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamiento;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoPartido;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferencia;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferenciaCompra;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferenciaVenta;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoFormacion;

/**
 *
 * @author Farid
 */
@Stateless
public class RestClientEJB implements RestClientEJBLocal {

	@EJB
	private RestRequestBuilderFactoryLocal jatrikRequestBuilderFactory;

	@Override
	public String postNuevoUsuario(InfoUsuario usuario)
			throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeUsuarioCreateRequestBuilder();
		ClientResponse response = jerseyHttpRequestBuilder.post(
				ClientResponse.class, usuario);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.CREATED
				.getStatusCode()) {
			throw new RestClientException(
					"Error al crear Usuario, status code: "
							+ response.getStatusInfo().getReasonPhrase());
		} else {
			String usuarioLocation = response.getHeaders().get("location")
					.get(0);
			String[] partUrlSplitted = usuarioLocation.split("/");
			return partUrlSplitted[partUrlSplitted.length - 1];
		}
	}

	@Override
	public InfoEquipo getEquipo(long idEquipo) throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeEquipoGetRequestBuilder(idEquipo);
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);

		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"Error al obtener el equipo, status code: "
							+ response.getStatusInfo().getReasonPhrase());
		} else {
			return response.getEntity(InfoEquipo.class);
		}
	};

	public RestClientEJB() {
	}

	@Override
	public InfoUsuario validarYObtenerUsuario(String nick, String pass)
			throws RestClientException {

		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeUsuarioLoginRequestBuilder(nick, pass);
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"Error al realizar el login, reason: "
							+ response.getStatusInfo().getReasonPhrase());
		} else {
			return response.getEntity(InfoUsuario.class);
		}

	}

	@Override
	public InfoPartido getInfoPartido(long idPartido)
			throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makePartidoGetRequestBuilder(idPartido);
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"Error al obtener la informacion del partido, reason: "
							+ response.getStatusInfo().getReasonPhrase());
		} else {
			return response.getEntity(InfoPartido.class);
		}
	}

	@Override
	public String postEntrenamiento(InfoEntrenamiento entrenamiento)
			throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeEntrenamientoPostRequestBuilder();
		ClientResponse response = jerseyHttpRequestBuilder.post(
				ClientResponse.class, entrenamiento);
		String Resultado;
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			Resultado = "Ya has entrenado esta habilidad el día de hoy";
		} else {
			Resultado = "Has entrenado la habilidad correctamente";
		}
		return Resultado;

	}

	@Override
	public void simularPartido(Long partidoId) throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeSimularPartidoRequestBuilder(partidoId);
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo realizar el Entrenamiento, status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}
	}

	@Override
	public List<InfoUsuario> getUsuarios() {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeUsuariosGetRequest();
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo realizar el Entrenamiento, status code: "
							+ response.getStatusInfo().getReasonPhrase());
		} else {
			return response.getEntity(ArrayList.class);
		}
	}

	@Override
	public InfoFormacion getFormacionEstandar(Long equipoId) throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory.makeFormacionEstandarRequestBuilder(equipoId);
		ClientResponse response = jerseyHttpRequestBuilder.get(ClientResponse.class);
		if(response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK.getStatusCode()){
			throw new RestClientException("Error al obtener formacion estandar "
					+ response.getStatusInfo().getReasonPhrase());			
		}else{
			return response.getEntity(InfoFormacion.class);
		}
	}

	@Override
	public InfoFormacion getFormacionProximoPartido(Long equipoId) throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory.makeFormacionProximoPartidoRequestBuilder(equipoId);
		ClientResponse response = jerseyHttpRequestBuilder.get(ClientResponse.class);
		if(response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK.getStatusCode()){
			throw new RestClientException("Error al obtener formacion proximo partido: "
					+ response.getStatusInfo().getReasonPhrase());			
		}else{
			return response.getEntity(InfoFormacion.class);
		}
	}

	@Override
	public void storeFormacionEstandar(Long equipoId, InfoFormacion infoFormacion) throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory.makeFormacionEstandarRequestBuilder(equipoId);
		ClientResponse response = jerseyHttpRequestBuilder.post(ClientResponse.class, infoFormacion);
		if(response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK.getStatusCode()){
			throw new RestClientException("Error al guardar formacion estandar: "
					+ response.getStatusInfo().getReasonPhrase());			
		};
	}

	@Override
	public void storeFormacionProximoPartido(Long equipoId, InfoFormacion infoFormacion) throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory.makeFormacionProximoPartidoRequestBuilder(equipoId);
		ClientResponse response = jerseyHttpRequestBuilder.post(ClientResponse.class, infoFormacion);
		if(response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK.getStatusCode()){
			throw new RestClientException("Error al guardar formacion proximo partido:"
					+ response.getStatusInfo().getReasonPhrase());			
		};
	}

	@Override
	public List<InfoTransferencia> getTransferencias() {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeTransferenciasGetRequest();
		ClientResponse response = (ClientResponse) jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo obtener el listado de transferencias, status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}

		return (List) response.getEntity(ArrayList.class);
	}

		
	@Override
	public List<InfoTransferencia> getTransferencias(Long idEquipo) {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeTransferenciasGetRequestBuilder(idEquipo);
		ClientResponse response = (ClientResponse) jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo obtener el listado de transferencias (idEquipo), status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}

		return (List) response.getEntity(ArrayList.class);
	}
	
	
	@Override
	public List<InfoJugador> getJugadoresEquipo(Long idEquipo) {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeJugadoresEquipoGetRequestBuilder(idEquipo);
		ClientResponse response = (ClientResponse) jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo obtener el listado de Jugadores del equipo (idEquipo), status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}

		return (List) response.getEntity(ArrayList.class);
	}
	
	@Override
	public String postTransferenciaVenta(InfoTransferenciaVenta venta)
			throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeUsuarioCreateRequestBuilder();
		ClientResponse response = (ClientResponse) jerseyHttpRequestBuilder
				.post(ClientResponse.class, venta);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.CREATED
				.getStatusCode()) {
			throw new RestClientException(
					"Error al crear la Venta, status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}

		String ventaLocation = (String) ((List) response.getHeaders().get(
				"location")).get(0);
		String[] partUrlSplitted = ventaLocation.split("/");
		return partUrlSplitted[(partUrlSplitted.length - 1)];
	}

	@Override
	public String postTransferenciaCompra(InfoTransferenciaCompra compra)
		    throws RestClientException
		  {
		    Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory.makeUsuarioCreateRequestBuilder();
		    ClientResponse response = (ClientResponse)jerseyHttpRequestBuilder.post(ClientResponse.class, compra);
		    if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.CREATED.getStatusCode()) {
		      throw new RestClientException("Error al crear la Compra, status code: " + response.getStatusInfo().getReasonPhrase());
		    }

		    String ventaLocation = (String)((List)response.getHeaders().get("location")).get(0);
		    String[] partUrlSplitted = ventaLocation.split("/");
		    return partUrlSplitted[(partUrlSplitted.length - 1)];
		  }

	@Override
	public List<InfoPartido> getPartidos() {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeGetPartidosBuilder();
		ClientResponse response = jerseyHttpRequestBuilder.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo obtener el listado de transferencias, status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}

		return (List) response.getEntity(ArrayList.class);
	}
}
