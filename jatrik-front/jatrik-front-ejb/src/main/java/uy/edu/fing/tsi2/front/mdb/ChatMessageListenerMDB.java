package uy.edu.fing.tsi2.front.mdb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.ejb3.annotation.ResourceAdapter;
import uy.edu.fing.tsi2.front.qualifier.ChatMessages;

/**
 *
 * @author Farid
 */
@MessageDriven(name = "ChatMessageListenerMDB", activationConfig = {
@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/ChatTopic"),
@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
@ResourceAdapter("ConnectionFactoryManaged")
public class ChatMessageListenerMDB implements MessageListener{

	@Inject
    @ChatMessages
    Event<ChatMessage> jmsEvent; 
	
	
	private static final Logger logger = Logger
			.getLogger(ChatMessageListenerMDB.class.getName());

	/**
	 *
	 * @param message
	 */
	@Override
	public void onMessage(Message message) {
		ObjectMessage om = (ObjectMessage) message;
		
		try {
			ChatMessage chatMessage = (ChatMessage) om.getObject();
			logger.log(Level.INFO, "Message received: {0}", chatMessage.getMessage());
			jmsEvent.fire(chatMessage);
		} catch (JMSException ex) {
			java.util.logging.Logger.getLogger(ChatMessageListenerMDB.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
}
