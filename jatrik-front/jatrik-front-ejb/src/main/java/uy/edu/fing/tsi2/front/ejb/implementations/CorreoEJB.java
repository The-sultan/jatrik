package uy.edu.fing.tsi2.front.ejb.implementations;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.front.ejb.interfaces.CorreoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.rest.client.interfaces.RestClientEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoCorreo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;

@Stateless
public class CorreoEJB implements CorreoEJBLocal {

	@EJB
	private RestClientEJBLocal jatrikCoreClient;

	@Override
	public List<InfoCorreo> obtenerCorreos(InfoUsuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCorreo(InfoCorreo nuevoCorreo) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateCorreo(InfoCorreo c){
		// TODO Auto-generated method stub
	}
}