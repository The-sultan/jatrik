/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.fing.tsi2.front.ejb.rest.client.exceptions;

/**
 *
 * @author Farid
 */
public class RestClientException extends RuntimeException{

	public RestClientException(String message) {
		super(message);
	}

	public RestClientException(Throwable cause) {
		super(cause);
	}

}
