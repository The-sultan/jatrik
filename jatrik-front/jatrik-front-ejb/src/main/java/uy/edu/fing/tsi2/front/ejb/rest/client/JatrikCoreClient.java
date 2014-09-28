package uy.edu.fing.tsi2.front.ejb.rest.client;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import uy.edu.fing.tsi2.front.ejb.rest.client.exceptions.JatrikClientException;
import uy.edu.fing.tsi2.jatrik.common.payloads.Usuario;

/**
 *
 * @author Farid
 */
@Stateless
public class JatrikCoreClient {

	@EJB
	private JatrikRequestBuilderFactory jatrikRequestBuilderFactory;
	
	public String postNewClassificationPart(Usuario usuario) throws JatrikClientException{
		Builder jerseyHttpRequestBuilder = jatrikRequestBuilderFactory.makeUsuarioCreateRequestBuilder();
		ClientResponse response = jerseyHttpRequestBuilder.post(ClientResponse.class, usuario);
		if(!response.getClientResponseStatus().equals(ClientResponse.Status.CREATED)){
            throw new JatrikClientException("Error al crear Usuario, status code: "
            + response.getClientResponseStatus());
            
        }
		else{
			String usuarioLocation = response.getHeaders().get("location").get(0);
			String[] partUrlSplitted = usuarioLocation.split("/");
            return partUrlSplitted[partUrlSplitted.length -1];
		}
	}
}
