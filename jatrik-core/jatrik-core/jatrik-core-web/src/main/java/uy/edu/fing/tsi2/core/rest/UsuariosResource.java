package uy.edu.fing.tsi2.core.rest;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import org.apache.commons.beanutils.BeanUtils;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;
import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerUsuarioLocal;


/**
 *
 * @author Farid
 */

@RequestScoped
public class UsuariosResource {
	
	@EJB
	private EJBManagerUsuarioLocal usuarioEJB;
	
	@POST	
	public Response crearUsuario(InfoUsuario usuario){
		Long usuarioId = usuarioEJB.crearUsuario(usuario.getNombre(), usuario.getNick(), usuario.getEmail(), usuario.getPassword(),
		usuario.getInfoEquipo().getNombre(), usuario.getInfoEquipo().getEstadio().getNombre());
		URI uri = null;
		try {
			uri = new URI("usuarios/" + usuarioId);
			
		} catch (URISyntaxException ex) {
			Logger.getLogger(UsuariosResource.class.getName()).log(Level.SEVERE, null, ex);
			return Response.serverError().build();
		}
		return Response.created(uri).build();
	}
	
	@GET
	public Response obtenerUsuarios(){
		List<Usuario> usuarios = usuarioEJB.obtenerUsuarios();
		try {
			return Response.ok(toPayload(usuarios)).build();
		} catch (IllegalAccessException | InvocationTargetException ex) {
			Logger.getLogger(UsuariosResource.class.getName()).log(Level.SEVERE, null, ex);
			return Response.serverError().build();
		}
	}
	
	private List<InfoUsuario> toPayload(List<Usuario> usuarios) throws IllegalAccessException, InvocationTargetException{
		List<InfoUsuario> usuariosPayload = new ArrayList<>();
		for(Usuario usuario : usuarios){
			InfoUsuario infoUsuario = new InfoUsuario();
			BeanUtils.copyProperties(infoUsuario, usuario);
			usuariosPayload.add(infoUsuario);
		}
		return usuariosPayload;
	}
	
		
}
