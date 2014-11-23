package uy.edu.fing.tsi2.jatrik.core.ejb.impl.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;

import uy.edu.fing.tsi2.jatrik.core.domain.DatosJugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Equipo;
import uy.edu.fing.tsi2.jatrik.core.domain.Habilidad;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.Usuario;
import uy.edu.fing.tsi2.jatrik.core.ejb.IUsuarios;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerUsuarioLocal;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.remote.EJBManagerUsuarioRemote;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumHabilidad;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoJugador;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMDatosJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMEquiposLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMJugadoresLocal;
import uy.edu.fing.tsi2.jatrik.core.persistence.impl.local.EJBEMUsuariosLocal;

/**
 *
 * @author c753388
 */

@Local(value = EJBManagerUsuarioLocal.class)
@Remote(value = EJBManagerUsuarioRemote.class)
@Stateless
public class EJBManagerUsuarioBean implements IUsuarios {

	private static final Logger logger = Logger
			.getLogger(EJBManagerUsuarioBean.class);

	@EJB
	private EJBEMUsuariosLocal usuarios;

	@EJB
	private EJBEMEquiposLocal equipos;

	@EJB
	private EJBEMJugadoresLocal jugadores;

	@EJB
	private EJBEMDatosJugadoresLocal datosJugadores;

	@Override
	public Long crearUsuario(InfoUsuario infoUsuario) {
		try {
			logger.info("Voy a Validar al usuario");
			if (usuarios.findUsuarioByNick(infoUsuario.getNick()) == null) {

				// TODO Aca deberiamos controlar si no hay equipos libres o si
				// no
				// hay mas jugadores
				Usuario usr = new Usuario();
				usr.setNombre(infoUsuario.getNombre());
				usr.setEmail(infoUsuario.getEmail());
				usr.setPassword(infoUsuario.getPassword());
				usr.setNick(infoUsuario.getNick());
				// BeanUtils.copyProperties(usr, infoUsuario);

				// Equipo equipo = equipos.findEquipoLibre();
				// equipo.setLatitud(latitud);
				// equipo.setLongitud(longitud);
				// equipo.setAltura(altura);
				// equipo.setNombre(nombreEquipo);
				// equipo.setEstadio(nombreEstadio);
				// equipo.setUsuario(usr);
				// equipos.update(equipo);

				Equipo equipo = new Equipo();
				equipo.setNombre(infoUsuario.getInfoEquipo().getNombre());
				equipo.setEstadio(infoUsuario.getInfoEquipo().getEstadio().getNombre());
				// infoUsuario.getInfoEquipo());

				// Seteo el Usuario al Equipo
				equipo.setUsuario(usr);

				//inicializaEquipo(equipo);
				usr.setEquipo(equipo);
				usuarios.add(usr);
				logger.info("Usuario Creado");
				return usr.getId();
			} else {
				// Ver que retornamos al servicio rest
				logger.info("El usuario ya Existe");
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;

		}
	}

	

	public Usuario validarUsuario(String nick, String password) {
		Usuario usuario = this.usuarios.findUsuarioByNick(nick);
		if (usuario != null) {
			if (usuario.getNick().equals(nick)
					&& usuario.getPassword().equals(password)) {
				return usuario;
			}
		}
		return null;
	}
	
	public List<Usuario> obtenerUsuarios(){
		return usuarios.findAll();
	}
	
	public boolean registrationIdUsuario(Long idUsuario, String registrationId){
		Usuario u = usuarios.find(idUsuario);
		if (u != null){
			u.setRegistrationId(registrationId);
			usuarios.update(u);
			return true;
		} else {
			return false;
		}
	}
	
	// --- Para notificaciones PUSH
	
	public void EnviarMensajePush(Long idUsuario, String mensaje){
	    String idRegistro=recuperarIdRegistro(idUsuario);
	    if (idRegistro != null){
		    JsonObject jsonObject = new JsonObject();
		    JsonObject data = new JsonObject();
		    data.addProperty("mensaje",mensaje);
		    JsonArray registration_ids = new JsonArray();
		    registration_ids.add(new JsonPrimitive(idRegistro));
		    jsonObject.add("data",data);
		    jsonObject.add("registration_ids",registration_ids);
		    try {
				invocarServicioGCM(jsonObject.toString(),new URL("https://android.googleapis.com/gcm/send"),"AIzaSyCAjEk51D89ZQfln5fwYOE5JnbPsxnc6gs");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
    public String recuperarIdRegistro(Long idUsuario) {
    	Usuario u = usuarios.find(idUsuario);  
	    return u.getRegistrationId();
	}
    
    public static final String invocarServicioGCM(final String json, final URL url,final String apikey){
	    try {
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("POST");
	      conn.setRequestProperty("Content-Type", "application/json");
	      conn.setRequestProperty("Accept-Encoding", "application/json");
	      //Se pasa el Api key como parametro de la cabecera de la petición http
	      conn.setRequestProperty("Authorization","key=" +apikey);
	      if(json!=null){
	        conn.setDoOutput(true);
	        OutputStream os = conn.getOutputStream();
	        os.write(json.getBytes("UTF-8"));
	        os.flush();
	      }
	 
	      if (conn.getResponseCode() != 200) {
	        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	      }
	      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String outputLine;
	      StringBuffer totalSalida = new StringBuffer();
	      System.out.println("Output from Server .... \n");
	      while ((outputLine = br.readLine()) != null) {
	        totalSalida.append(outputLine/*new String(outputLine.getBytes("ISO-8859-1"), "UTF-8")*/);
	      }
	      conn.disconnect();
	      return totalSalida.toString();
	    } catch (MalformedURLException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
	 
}
