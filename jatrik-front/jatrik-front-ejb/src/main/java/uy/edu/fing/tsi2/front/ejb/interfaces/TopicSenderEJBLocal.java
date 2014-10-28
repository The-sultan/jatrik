package uy.edu.fing.tsi2.front.ejb.interfaces;

import javax.ejb.Local;
import uy.edu.fing.tsi2.front.mdb.ChatMessage;

/**
 * @author Farid
 */
@Local
public interface TopicSenderEJBLocal {
	void sendMessage(ChatMessage message);
}
