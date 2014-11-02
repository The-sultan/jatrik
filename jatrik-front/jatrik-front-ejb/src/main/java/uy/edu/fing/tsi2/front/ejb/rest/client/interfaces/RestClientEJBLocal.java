package uy.edu.fing.tsi2.front.ejb.rest.client.interfaces;

import java.util.List;
import javax.ejb.Local;

import uy.edu.fing.tsi2.front.ejb.rest.client.exceptions.RestClientException;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamiento;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoFormacion;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoPartido;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;

/**
 * @author Farid
 */
@Local
public interface RestClientEJBLocal {
	
	public String postNuevoUsuario(InfoUsuario usuario) throws RestClientException;
	
	public InfoUsuario validarYObtenerUsuario(String nick, String pass) throws RestClientException;

	public InfoEquipo getEquipo(long idEquipo) throws RestClientException;
	
	public InfoPartido getInfoPartido(long idPartido)  throws RestClientException;

	String postEntrenamiento(InfoEntrenamiento entrenamiento) throws RestClientException;
	
	void simularPartido(Long partidoId) throws RestClientException;
	
	InfoFormacion getFormacionEstandar(Long equipoId) throws RestClientException;
	
	InfoFormacion getFormacionProximoPartido(Long equipoId) throws RestClientException;
	
	void storeFormacionEstandar(Long equipoId, InfoFormacion infoFormacion) throws RestClientException;
	
	void storeFormacionProximoPartido(Long equipoId, InfoFormacion infoFormacion) throws RestClientException;
	
	List<InfoUsuario> getUsuarios();

}
