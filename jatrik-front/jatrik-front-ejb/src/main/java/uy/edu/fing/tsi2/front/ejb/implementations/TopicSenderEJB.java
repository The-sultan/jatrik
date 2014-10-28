package uy.edu.fing.tsi2.front.ejb.implementations;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import uy.edu.fing.tsi2.front.ejb.interfaces.TopicSenderEJBLocal;
import uy.edu.fing.tsi2.front.mdb.ChatMessage;

/**
 * @author Farid
 */
@Stateless
public class TopicSenderEJB implements TopicSenderEJBLocal{
	
	@Resource(mappedName = "java:/ConnectionFactoryManaged")
	private ConnectionFactory connectionFactory;


	@Resource(mappedName = "java:/topic/ChatTopic")
	private Topic topic;

	@Override
	public void sendMessage(ChatMessage message){
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(topic);
			connection.start();
			
			ObjectMessage jmsMessage = session.createObjectMessage(message);
			
			messageProducer.send(jmsMessage);
		} catch (JMSException ex) {
			Logger.getLogger(TopicSenderEJB.class.getName()).log(Level.SEVERE, null, ex);
		}

	} 
}
