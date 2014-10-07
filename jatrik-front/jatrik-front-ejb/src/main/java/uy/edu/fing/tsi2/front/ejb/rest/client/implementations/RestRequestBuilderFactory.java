package uy.edu.fing.tsi2.front.ejb.rest.client.implementations;

import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestRequestBuilderFactoryLocal;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author Farid
 */

@Stateless
public class RestRequestBuilderFactory implements RestRequestBuilderFactoryLocal{

	private static final String JATRIK_CORE_URL = "http://localhost:8080/jatrik-core-web/rest";
	
	
	private Client crearClienteJersey() {
		DefaultClientConfig dcc = new DefaultClientConfig();
		dcc.getClasses().add(JacksonJsonProvider.class);
		dcc.getFeatures().put("com.sun.jersey.api.json.POJOMappingFeature", Boolean.TRUE);
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
		dcc.getSingletons().add(new JacksonJsonProvider(om));
		Client client = Client.create(dcc);
		return client;
    }
    
	@Override
    public Builder makeUsuarioCreateRequestBuilder() {
        String url = JATRIK_CORE_URL + "/usuarios";
        return crearClienteJersey()
                .resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
	}

	public RestRequestBuilderFactory() {
	}
	
	
}