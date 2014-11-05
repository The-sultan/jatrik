package uy.edu.fing.tsi2.front.ejb.implementations;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.front.ejb.interfaces.CorreoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoCorreo;

@Stateless
public class CorreoEJB implements CorreoEJBLocal {

	@EJB
	private RestClientEJBLocal jatrikCoreClient;

	@Override
	public List<InfoCorreo> obtenerCorreos(Long idUsuario) {
		return jatrikCoreClient.getCorreosUsuario(idUsuario);
	}

	@Override
	public void addCorreo(InfoCorreo nuevoCorreo) {
		jatrikCoreClient.postNuevoCorreo(nuevoCorreo);
		
	}
	
	@Override
	public void updateCorreo(InfoCorreo c){
		jatrikCoreClient.postUpdateCorreo(c);
	}
}