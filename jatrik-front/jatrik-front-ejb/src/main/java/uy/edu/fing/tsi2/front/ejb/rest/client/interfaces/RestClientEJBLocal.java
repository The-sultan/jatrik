package uy.edu.fing.tsi2.front.ejb.rest.client.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.edu.fing.tsi2.front.ejb.rest.client.exceptions.RestClientException;
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

/**
 * @author Farid
 */
@Local
public interface RestClientEJBLocal {
	
	public String postNuevoUsuario(InfoUsuario usuario) throws RestClientException;
	
	public InfoUsuario validarYObtenerUsuario(String nick, String pass) throws RestClientException;

	public InfoEquipo getEquipo(long idEquipo) throws RestClientException;
	
	public InfoPartido getInfoPartido(long idPartido)  throws RestClientException;
	
	public Boolean getPuedeEntrenarEquipo(long idEquipo)  throws RestClientException;

	public Boolean postEntrenamiento(InfoEntrenamiento entrenamiento) throws RestClientException;
	
	void simularPartido(Long partidoId) throws RestClientException;
	
	InfoFormacion getFormacionEstandar(Long equipoId) throws RestClientException;
	
	InfoFormacion getFormacionProximoPartido(Long equipoId) throws RestClientException;
	
	void storeFormacionEstandar(Long equipoId, InfoFormacion infoFormacion) throws RestClientException;
	
	void storeFormacionProximoPartido(Long equipoId, InfoFormacion infoFormacion) throws RestClientException;
	
	List<InfoUsuario> getUsuarios();
	
	List<InfoTransferencia> getTransferencias();

	String postTransferenciaVenta(InfoTransferenciaVenta paramInfoTransferenciaVenta)
	    throws RestClientException;

	String postTransferenciaCompra(InfoTransferenciaCompra paramInfoTransferenciaCompra)
	    throws RestClientException;

	List<InfoPartido> getPartidos();

	List<InfoTransferencia> getTransferencias(Long idEquipo);

	List<InfoJugador> getJugadoresEquipo(Long idEquipo);

	List<InfoCorreo> getCorreosUsuario(Long idUsuario);

	String postNuevoCorreo(InfoCorreo nuevoCorreo) throws RestClientException;

	String postUpdateCorreo(InfoCorreo updateCorreo) throws RestClientException;


}
