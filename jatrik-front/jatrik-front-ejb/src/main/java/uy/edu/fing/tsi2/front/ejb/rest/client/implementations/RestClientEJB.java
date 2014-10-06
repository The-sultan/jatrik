package uy.edu.fing.tsi2.front.ejb.rest.client.implementations;

import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestRequestBuilderFactoryLocal;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.front.ejb.rest.client.exceptions.RestClientException;
import uy.edu.fing.tsi2.jatrik.common.payloads.Usuario;

/**
 *
 * @author Farid
 */
@Stateless
public class RestClientEJB implements RestClientEJBLocal{

	@EJB
	private RestRequestBuilderFactoryLocal jatrikRequestBuilderFactory;
	
	@Override
	public String postNuevoUsuario(Usuario usuario) throws RestClientException{
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory.makeUsuarioCreateRequestBuilder();
		ClientResponse response = jerseyHttpRequestBuilder.post(ClientResponse.class, usuario);
		if(response.getStatusInfo().getStatusCode() != ClientResponse.Status.CREATED.getStatusCode()){
			throw new RestClientException("Error al crear Usuario, status code: "
					+ response.getStatusInfo().getReasonPhrase());			
		}
		else{
			String usuarioLocation = response.getHeaders().get("location").get(0);
			String[] partUrlSplitted = usuarioLocation.split("/");
			return partUrlSplitted[partUrlSplitted.length -1];
		}
	}

	public RestClientEJB() {
	}
	
	
}
