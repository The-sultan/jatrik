package uy.edu.fing.tsi2.front.mdb;

import java.io.Serializable;

/**
 * @author Farid
 */
public class ChatMessage implements Serializable {
	
	private String message;
	
	private String sender;
	
	private String receiver;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	
}


