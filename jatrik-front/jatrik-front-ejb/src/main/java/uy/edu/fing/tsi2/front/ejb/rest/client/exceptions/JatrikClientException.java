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
public class JatrikClientException extends RuntimeException{

	public JatrikClientException(String message) {
		super(message);
	}

	public JatrikClientException(Throwable cause) {
		super(cause);
	}

}
