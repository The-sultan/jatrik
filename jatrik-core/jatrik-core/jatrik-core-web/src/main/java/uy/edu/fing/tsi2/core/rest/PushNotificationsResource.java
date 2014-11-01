package uy.edu.fing.tsi2.core.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.edu.fing.tsi2.jatrik.common.payloads.RequestRegistrationId;
import uy.edu.fing.tsi2.jatrik.common.payloads.ResponseRegistrationId;
import uy.edu.fing.tsi2.jatrik.core.ejb.impl.local.EJBManagerUsuarioLocal;

@RequestScoped
public class PushNotificationsResource {
	
	public static final String PATH = "C:\\Users\\Usuario\\Desktop\\registration-id.txt";
	
	@EJB
	EJBManagerUsuarioLocal usuarioEJB;
	
	  @POST
	  @Path("/add")
	  public ResponseRegistrationId addRegistationId(
	      RequestRegistrationId requestRegisterId) {
	    ResponseRegistrationId responseRegistrationId = new ResponseRegistrationId();
	    try {
	      usuarioEJB.registrationIdUsuario(requestRegisterId.getIdUsuario(), requestRegisterId.getRegistrationId());
	      usuarioEJB.EnviarMensajePush(requestRegisterId.getIdUsuario(), "Holaaaa");
	      responseRegistrationId.setCodeResponse(ResponseRegistrationId.OK);
	      responseRegistrationId
	          .setMessageResponse("Registro efectuado satisfactoriamente");
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      responseRegistrationId.setCodeResponse(ResponseRegistrationId.KAO);
	      responseRegistrationId.setMessageResponse(e.getMessage());
	    }
	    return responseRegistrationId;
	  }
	  

}