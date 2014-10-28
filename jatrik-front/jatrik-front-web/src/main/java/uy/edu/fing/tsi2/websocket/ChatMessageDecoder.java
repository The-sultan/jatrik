package uy.edu.fing.tsi2.websocket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import org.codehaus.jackson.map.ObjectMapper;
import uy.edu.fing.tsi2.front.mdb.ChatMessage;

/**
 *
 * @author Farid
 */
public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {
	@Override
	public void init(final EndpointConfig config) {
	}
 
	@Override
	public void destroy() {
	}
 
	@Override
	public ChatMessage decode(final String textMessage) {
		try {
			ObjectMapper om = new ObjectMapper();
			ChatMessage chatMessage = om.readValue(textMessage, ChatMessage.class);
			return chatMessage;
		} catch (IOException ex) {
			Logger.getLogger(ChatMessageDecoder.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException(ex);
		}
	}
 
	@Override
	public boolean willDecode(final String s) {
		return true;
	}
}
