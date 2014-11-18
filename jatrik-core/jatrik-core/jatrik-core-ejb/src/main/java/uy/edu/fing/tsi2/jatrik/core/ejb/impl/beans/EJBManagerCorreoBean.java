package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import uy.edu.fing.tsi2.jatrik.core.domain.Correo;
import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;
import uy.edu.fing.tsi2.jatrik.core.ejb.ICorreos;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerCorreoLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerCorreoRemote;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMCorreosLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMUsuariosLocal;



@Stateless
@Local(EJBManagerCorreoLocal.class)
@Remote(EJBManagerCorreoRemote.class)
public class EJBManagerCorreoBean implements ICorreos {

	@EJB
	private EJBEMCorreosLocal correos;
	
	@EJB
	private EJBEMUsuariosLocal usuarios;
	
	public Correo addCorreo(Long to,Long from,String asunto,String mensaje) {
		Usuario toUser = usuarios.find(to);
		Usuario fromUser = usuarios.find(from);
		Correo correo = new Correo(asunto,mensaje,false,new Date(),fromUser,toUser);
		return correos.add(correo);
	}

	
	public void deleteCorreo(Long id) {
		Correo correo = correos.find(id);
		correos.delete(correo);
	}

	
	public Correo findCorreo(Long id) {
		return correos.find(id);
	}

	
	public Correo leerCorreo(Long id) {
		Correo correo = correos.find(id);
		correo.setLeido(true);
		return correos.update(correo);
	}

	
	public List<Correo> obtenerCorreos(Long usuarioId) {
		return correos.findCorreosUsuario(usuarioId);
	}

}
