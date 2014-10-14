package uy.edu.fing.tsi2.front.ejb.rest.client.interfaces;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import javax.ejb.Local;

/**
 * @author Farid
 */
@Local
public interface RestRequestBuilderFactoryLocal {
	public WebResource.Builder makeUsuarioCreateRequestBuilder();

	public Builder makeEquipoGetRequestBuilder(long idEquipo);
	
	public Builder makeUsuarioLoginRequestBuilder(String nick, String pass);
	
	public Builder makePartidoGetRequestBuilder(long idPartido);

	public WebResource.Builder makeEntrenamientoPostRequestBuilder();
}
