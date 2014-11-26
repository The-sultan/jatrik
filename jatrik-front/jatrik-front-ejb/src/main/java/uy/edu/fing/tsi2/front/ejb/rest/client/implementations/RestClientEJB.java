package uy.edu.fing.tsi2.front.ejb.rest.client.implementations;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.front.ejb.rest.client.exceptions.RestClientException;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestRequestBuilderFactoryLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoCorreo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamiento;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoFormacion;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoPartido;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferencia;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferenciaCompra;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoTransferenciaVenta;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource.Builder;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoLiga;

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
	public Boolean postEntrenamiento(InfoEntrenamiento entrenamiento)
			throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeEntrenamientoPostRequestBuilder();
		ClientResponse response = jerseyHttpRequestBuilder.post(
				ClientResponse.class, entrenamiento);
		Boolean Resultado;
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			Resultado = false;
		} else {
			Resultado = true;
		}
		return Resultado;

	}

	public Boolean getPuedeEntrenarEquipo(long equipoId)
			throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeEntrenamientoGetRequestBuilder(equipoId);
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"Error al obtener entrenamiento del equipo "
							+ response.getStatusInfo().getReasonPhrase());
		} else {
			return response.getEntity(Boolean.class);
		}
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
			GenericType<List<InfoUsuario>> gType = new GenericType<List<InfoUsuario>>() {
			};
			return response.getEntity(gType);
		}
	}

	@Override
	public InfoFormacion getFormacionEstandar(Long equipoId)
			throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeFormacionEstandarRequestBuilder(equipoId);
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"Error al obtener formacion estandar "
							+ response.getStatusInfo().getReasonPhrase());
		} else {
			return response.getEntity(InfoFormacion.class);
		}
	}

	@Override
	public InfoFormacion getFormacionProximoPartido(Long equipoId)
			throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeFormacionProximoPartidoRequestBuilder(equipoId);
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"Error al obtener formacion proximo partido: "
							+ response.getStatusInfo().getReasonPhrase());
		} else {
			return response.getEntity(InfoFormacion.class);
		}
	}

	@Override
	public void storeFormacionEstandar(Long equipoId,
			InfoFormacion infoFormacion) throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeFormacionEstandarRequestBuilder(equipoId);
		ClientResponse response = jerseyHttpRequestBuilder.post(
				ClientResponse.class, infoFormacion);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"Error al guardar formacion estandar: "
							+ response.getStatusInfo().getReasonPhrase());
		}
		;
	}

	@Override
	public void storeFormacionProximoPartido(Long equipoId,
			InfoFormacion infoFormacion) throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeFormacionProximoPartidoRequestBuilder(equipoId);
		ClientResponse response = jerseyHttpRequestBuilder.post(
				ClientResponse.class, infoFormacion);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"Error al guardar formacion proximo partido:"
							+ response.getStatusInfo().getReasonPhrase());
		}
		;
	}

	@Override
	public List<InfoTransferencia> getTransferencias() {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeTransferenciasGetRequest();
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo obtener el listado de transferencias, status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}
		GenericType<List<InfoTransferencia>> gType = new GenericType<List<InfoTransferencia>>() {
		};
		return (List) response.getEntity(gType);
	}

	@Override
	public List<InfoTransferencia> getTransferencias(Long idEquipo) {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeTransferenciasGetRequestBuilder(idEquipo);
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo obtener el listado de transferencias (idEquipo), status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}
		GenericType<List<InfoTransferencia>> gType = new GenericType<List<InfoTransferencia>>() {
		};
		return (List) response.getEntity(gType);
	}

	@Override
	public List<InfoJugador> getJugadoresEquipo(Long idEquipo) {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeJugadoresEquipoGetRequestBuilder(idEquipo);
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo obtener el listado de Jugadores del equipo (idEquipo), status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}
		GenericType<List<InfoJugador>> gType = new GenericType<List<InfoJugador>>() {
		};
		return response.getEntity(gType);
	}

	@Override
	public String postTransferenciaVenta(InfoTransferenciaVenta venta)
			throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeTransferenciasVenderGetRequest();
		ClientResponse response = jerseyHttpRequestBuilder.post(
				ClientResponse.class, venta);
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
			throws RestClientException {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeTransferenciasComprarGetRequest();
		ClientResponse response = jerseyHttpRequestBuilder.post(
				ClientResponse.class, compra);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.CREATED
				.getStatusCode()) {
			throw new RestClientException(
					"Error al crear la Compra, status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}

		String ventaLocation = (String) ((List) response.getHeaders().get(
				"location")).get(0);
		String[] partUrlSplitted = ventaLocation.split("/");
		return partUrlSplitted[(partUrlSplitted.length - 1)];
	}

	@Override
	public List<InfoPartido> getPartidos() {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeGetPartidosBuilder();
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo obtener el listado de transferencias, status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}
		GenericType<List<InfoPartido>> gType = new GenericType<List<InfoPartido>>() {
		};
		return (List) response.getEntity(gType);
	}

	@Override
	public List<InfoCorreo> getCorreosUsuario(Long idUsuario) {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeCorreosGetRequestBuilder(idUsuario);
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo obtener el listado de correos del usuario (idUsuario), status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}
		GenericType<List<InfoCorreo>> gType = new GenericType<List<InfoCorreo>>() {};
		
		return (List) response.getEntity(gType);
	}

        @Override
        
	public List<InfoCorreo> getCorreosEnviadosUsuario(Long idUsuario) {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeCorreosEnviadosGetRequestBuilder(idUsuario);
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo obtener el listado de correos enviados del usuario (idUsuario), status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}
		GenericType<List<InfoCorreo>> gType = new GenericType<List<InfoCorreo>>() {};
		
		return (List) response.getEntity(gType);
	}
        
	@Override
	public String postNuevoCorreo(InfoCorreo nuevoCorreo)
			throws RestClientException {

		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeCorreoEnviarPostRequestBuilder();
		ClientResponse response = jerseyHttpRequestBuilder.post(
				ClientResponse.class, nuevoCorreo);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.CREATED
				.getStatusCode()) {
			throw new RestClientException(
					"Error al crear el Correo Nuevo, status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}
		String ventaLocation = (String) ((List) response.getHeaders().get(
				"location")).get(0);
		String[] partUrlSplitted = ventaLocation.split("/");
		return partUrlSplitted[(partUrlSplitted.length - 1)];
	}
	
	@Override
	public String postUpdateCorreo(InfoCorreo updateCorreo)
			throws RestClientException {

		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeCorreoUpdatePostRequestBuilder();
		ClientResponse response = jerseyHttpRequestBuilder.post(
				ClientResponse.class, updateCorreo);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.CREATED
				.getStatusCode()) {
			throw new RestClientException(
					"Error al crear el Correo Nuevo, status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}
		String ventaLocation = (String) ((List) response.getHeaders().get(
				"location")).get(0);
		String[] partUrlSplitted = ventaLocation.split("/");
		return partUrlSplitted[(partUrlSplitted.length - 1)];
	}
        
        
        @Override        
	public List<InfoLiga> getInformacionLiga(Long idLiga) {
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory
				.makeGetInfoLiga(idLiga);
		ClientResponse response = jerseyHttpRequestBuilder
				.get(ClientResponse.class);
		if (response.getStatusInfo().getStatusCode() != ClientResponse.Status.OK
				.getStatusCode()) {
			throw new RestClientException(
					"No se pudo obtener el listado de infoLiga (idLiga), status code: "
							+ response.getStatusInfo().getReasonPhrase());
		}
		GenericType<List<InfoLiga>> gType = new GenericType<List<InfoLiga>>() {};
		
		return (List) response.getEntity(gType);
	}
        
}
