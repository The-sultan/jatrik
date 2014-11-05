package uy.edu.fing.tsi2.front.ejb.rest.client.interfaces;

import javax.ejb.Local;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoCorreo;

import com.sun.jersey.api.client.WebResource.Builder;

/**
 * @author Farid
 */
@Local
public interface RestRequestBuilderFactoryLocal {
	
	Builder makeUsuarioCreateRequestBuilder();

	Builder makeEquipoGetRequestBuilder(long idEquipo);
	
	Builder makeUsuarioLoginRequestBuilder(String nick, String pass);
	
	Builder makePartidoGetRequestBuilder(long idPartido);

	Builder makeEntrenamientoPostRequestBuilder();
	
	Builder makeSimularPartidoRequestBuilder(Long partidoId);
	
	Builder makeUsuariosGetRequest();
	
	Builder makeTransferenciasGetRequest();

	Builder makeTransferenciasComprarGetRequest();

	Builder makeTransferenciasVenderGetRequest();
	
	Builder makeFormacionEstandarRequestBuilder(Long idEquipo);
	
	Builder makeFormacionProximoPartidoRequestBuilder(Long idEquipo);
	
	Builder makeGetPartidosBuilder();

	Builder makeTransferenciasGetRequestBuilder(Long idEquipo);

	Builder makeJugadoresEquipoGetRequestBuilder(Long idEquipo);

	Builder makeCorreosGetRequestBuilder(Long idUsuario);

	Builder makeCorreoEnviarPostRequestBuilder();

	
	
}
