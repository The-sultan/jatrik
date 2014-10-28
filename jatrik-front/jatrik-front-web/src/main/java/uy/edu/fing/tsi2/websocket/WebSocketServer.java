package uy.edu.fing.tsi2.websocket;


import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import uy.edu.fing.tsi2.front.ejb.interfaces.TopicSenderEJBLocal;
import uy.edu.fing.tsi2.front.mdb.ChatMessage;
import uy.edu.fing.tsi2.front.qualifier.ChatMessages;


/**
 * @author Farid
 */


@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class, encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class WebSocketServer implements Serializable{
	
	private static Session session;
	
	private static final Map<Session, String> sessionsToNick = 
							Collections.synchronizedMap(new HashMap<Session, String>());
	
	//@EJB
	//TopicSenderEJBLocal topicSenderEJB;
	
	@OnOpen public void onOpen(final Session session, EndpointConfig config) throws IOException { 
		HttpSession httpSession = (HttpSession) config.getUserProperties()
                                           .get(HttpSession.class.getName());
		String nick = (String)httpSession.getAttribute("nick");
		if(nick == null){
			session.close();
		}
		session.setMaxIdleTimeout(0);
		session.getUserProperties().put("nick", nick);
		WebSocketServer.session = session;
		/*
		Set<Session> sessionsForUser;
		if(sessions.containsKey(nick)){
			sessionsForUser = sessions.get(nick);
		}else{
			sessionsForUser = new HashSet<>();
		}
		sessionsForUser.add(session);
		sessions.put(nick,sessionsForUser);
		sessionsToNick.put(session, nick);*/
				
	}
	
	@OnMessage public void onMessage(ChatMessage chatMessage, final Session client) { 
		TopicSenderEJBLocal topicSenderEJB = getContextualBeanInstance(TopicSenderEJBLocal.class);
		topicSenderEJB.sendMessage(chatMessage);
	}
	
	@OnClose public void onClose(final Session session) { 
		
	}
	
	@OnError public void onError(final Session session, Throwable t){
		try {
			Logger.getLogger(WebSocketServer.class.getName()).log(Level.SEVERE, null, t);
			session.close();
		} catch (IOException ex) {
			Logger.getLogger(WebSocketServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public static void onJMSMessage(@Observes @ChatMessages ChatMessage chatMessage) throws EncodeException {
		if(session != null){
			for(Session destionationSession : session.getOpenSessions()){
			if(destionationSession.getUserProperties().get("nick") != null &&
					destionationSession.getUserProperties().get("nick").equals(chatMessage.getReceiver())){
					try {
						destionationSession.getBasicRemote().sendObject(chatMessage);
					} catch (IOException ex) {
						Logger.getLogger(WebSocketServer.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}
	}
	
	public static <B> B getContextualBeanInstance(Class<B> type) {
		try {
			BeanManager beanManager = InitialContext.doLookup("java:comp/BeanManager");
			Set<Bean<?>> beans = beanManager.getBeans(type);
			Bean<?> bean = beanManager.resolve(beans);
			CreationalContext<?> cc = beanManager.createCreationalContext(bean);
			return (B) beanManager.getReference(bean, type, cc);
		} catch (NamingException e) {
			throw new RuntimeException("", e);
		}
	}
}
