package uy.edu.fing.tsi2.jatrik.common.payloads;

public class RequestRegistrationId {
	  //Identificador del registro
	  private String registrationId;
	  private Long idUsuario;
	 
	  public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getRegistrationId() {
	    return registrationId;
	  }
	 
	  public void setRegistrationId(String registrationId) {
	    this.registrationId = registrationId;
	  }
	}