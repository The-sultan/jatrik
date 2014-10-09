package uy.edu.fing.tsi2.front.ejb.rest.client.exceptions;

/**
 *
 * @author Farid
 */
public class RestClientException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -867296431502386215L;

	public RestClientException(String message) {
		super(message);
	}

	public RestClientException(Throwable cause) {
		super(cause);
	}

}
