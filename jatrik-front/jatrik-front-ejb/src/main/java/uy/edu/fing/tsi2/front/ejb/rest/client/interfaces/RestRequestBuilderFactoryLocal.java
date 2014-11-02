package uy.edu.fing.tsi2.front.ejb.rest.client.interfaces;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import javax.ejb.Local;

/**
 * @author Farid
 */
@Local
public interface RestRequestBuilderFactoryLocal {
	WebResource.Builder makeUsuarioCreateRequestBuilder();

	Builder makeEquipoGetRequestBuilder(long idEquipo);
	
	Builder makeUsuarioLoginRequestBuilder(String nick, String pass);
	
	Builder makePartidoGetRequestBuilder(long idPartido);

	Builder makeEntrenamientoPostRequestBuilder();
	
	Builder makeSimularPartidoRequestBuilder(Long partidoId);
	
	Builder makeUsuariosGetRequest();
	
	Builder makeFormacionEstandarRequestBuilder(Long idEquipo);
	
	Builder makeFormacionProximoPartidoRequestBuilder(Long idEquipo);
	
}
