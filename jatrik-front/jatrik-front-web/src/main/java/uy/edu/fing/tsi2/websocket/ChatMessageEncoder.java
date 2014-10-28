/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.fing.tsi2.websocket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import org.codehaus.jackson.map.ObjectMapper;
import uy.edu.fing.tsi2.front.mdb.ChatMessage;


/**
 *
 * @author Farid
 */
public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
	@Override
	public void init(final EndpointConfig config) {
	}
 
	@Override
	public void destroy() {
	}
 
	@Override
	public String encode(final ChatMessage chatMessage) throws EncodeException {
		try {
			ObjectMapper om = new ObjectMapper();
			return om.writeValueAsString(chatMessage);
		} catch (IOException ex) {
			Logger.getLogger(ChatMessageEncoder.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException();
		}
	}
}
