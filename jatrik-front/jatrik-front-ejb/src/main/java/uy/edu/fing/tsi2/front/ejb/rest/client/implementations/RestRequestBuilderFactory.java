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
public class RestRequestBuilderFactory implements
        RestRequestBuilderFactoryLocal {

    private static final String JATRIK_CORE_URL = "http://localhost:8080/jatrik-core-web/rest";

    private Client crearClienteJersey() {
        DefaultClientConfig dcc = new DefaultClientConfig();
        dcc.getClasses().add(JacksonJsonProvider.class);
        dcc.getFeatures().put("com.sun.jersey.api.json.POJOMappingFeature",
                Boolean.TRUE);
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                Boolean.FALSE);
        dcc.getSingletons().add(new JacksonJsonProvider(om));
        Client client = Client.create(dcc);
        return client;
    }

    @Override
    public Builder makeUsuarioCreateRequestBuilder() {
        String url = JATRIK_CORE_URL + "/usuarios";
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeEquipoGetRequestBuilder(long idEquipo) {
        String url = JATRIK_CORE_URL + "/equipos/" + Long.toString(idEquipo);
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    public RestRequestBuilderFactory() {
    }

    @Override
    public Builder makeUsuarioLoginRequestBuilder(String nick, String pass) {
        String url = String.format(JATRIK_CORE_URL
                + "/login?nick=%s&password=%s", nick, pass);
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makePartidoGetRequestBuilder(long idPartido) {
        String url = String.format(JATRIK_CORE_URL + "/partidos/%d", idPartido);
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeEntrenamientoPostRequestBuilder() {
        String url = String.format(JATRIK_CORE_URL + "/entrenamiento/");
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeEntrenamientoGetRequestBuilder(Long idEquipo) {
        String url = String.format(JATRIK_CORE_URL + "/entrenamiento/" + idEquipo + "/puedeEntrenar");
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeSimularPartidoRequestBuilder(Long partidoId) {
        String url = String.format(JATRIK_CORE_URL + "/simulacion?partido="
                + partidoId);
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeUsuariosGetRequest() {
        String url = String.format(JATRIK_CORE_URL + "/usuarios");
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeFormacionEstandarRequestBuilder(Long idEquipo) {
        String url = String.format(JATRIK_CORE_URL + "/equipos/" + idEquipo
                + "/formaciones/estandar");
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeTransferenciasGetRequest() {
        String url = String.format(JATRIK_CORE_URL + "/transferencia");
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeJugadoresEquipoGetRequestBuilder(Long idEquipo) {
        String url = String.format(JATRIK_CORE_URL + "/equipos/" + idEquipo
                + "/formaciones/propia");
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeTransferenciasGetRequestBuilder(Long idEquipo) {
        String url = String.format(JATRIK_CORE_URL + "/transferencia/"
                + idEquipo);
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeFormacionProximoPartidoRequestBuilder(Long idEquipo) {
        String url = String.format(JATRIK_CORE_URL + "/equipos/" + idEquipo
                + "/formaciones/estandar");
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    public Builder makeTransferenciasComprarGetRequest() {

        String url = String.format(JATRIK_CORE_URL + "/transferencia/comprar");
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    public Builder makeTransferenciasVenderGetRequest() {
        String url = String.format(JATRIK_CORE_URL + "/transferencia/vender");
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeGetPartidosBuilder() {
        String url = String.format(JATRIK_CORE_URL + "/partidos");
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeCorreosGetRequestBuilder(Long idUsuario) {
        String url = String.format(JATRIK_CORE_URL + "/correo/" + idUsuario);
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeCorreosEnviadosGetRequestBuilder(Long idUsuario) {
        String url = String.format(JATRIK_CORE_URL + "/correo/enviados/" + idUsuario);
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeCorreoEnviarPostRequestBuilder() {
        String url = String.format(JATRIK_CORE_URL + "/correo/send");
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeCorreoUpdatePostRequestBuilder() {
        String url = String.format(JATRIK_CORE_URL + "/correo/update");
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    @Override
    public Builder makeGetInfoLiga(Long idEquipo) {
        String url = String.format(JATRIK_CORE_URL + "/ligas/equipo/" + idEquipo);
        return crearClienteJersey().resource(url)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

}
