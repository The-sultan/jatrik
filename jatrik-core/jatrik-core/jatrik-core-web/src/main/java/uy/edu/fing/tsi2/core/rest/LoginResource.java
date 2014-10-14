package uy.edu.fing.tsi2.core.rest;

import java.lang.reflect.InvocationTargetException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;

import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;
import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerUsuarioLocal;

@RequestScoped
public class LoginResource {

	@EJB
	private EJBManagerUsuarioLocal usuarioEJB;
	
	@GET	
	@Produces("application/json")
	public Response validarYObtenerUsuario(@QueryParam("nick") String nick, @QueryParam("password") String password){
		Usuario usuario = usuarioEJB.validarUsuario(nick, password);
		InfoUsuario infoUsuario = new InfoUsuario();
		if(usuario != null){
			try {
				BeanUtils.copyProperties(infoUsuario, usuario);
				InfoEquipo infoEquipo = new InfoEquipo();
				infoEquipo.setId(usuario.getEquipo().getId());
				infoUsuario.setInfoEquipo(infoEquipo);
				return Response.ok(infoUsuario).build();
			} catch (IllegalAccessException | InvocationTargetException e) {
				return Response.status(Response.Status.UNAUTHORIZED).build();
				
			}
			
		}
		else{
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
}
