package uy.edu.fing.tsi2.front.ejb.rest.client.interfaces;

import javax.ejb.Local;

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
	
}
