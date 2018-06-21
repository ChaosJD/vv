package de.fhr.inf.vv.mq.ps;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;

public class NewsServer {

	public static final String NEWS_TOPIC_NAME = "NEWS_FEED";
	private Connection connection;
	private Session session;
	private MessageProducer updateProducer;

	protected NewsServer() {
		super();
	}

	public static NewsServer newNewsServer() throws Exception {
		NewsServer newsServer = new NewsServer();
		newsServer.initialize();
		return newsServer;
	}

	private void initialize() throws Exception {
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.activemq.jndi." +
				"ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL,
				"tcp://localhost:61616");
		System.out.println("[INFO] Erzeuge den Context, zum Zugriff auf den Namensdienst");
		Context ctx = new InitialContext(props);	
		System.out.println("[INFO] Beschaffe Zugriffsmoeglichkeit auf Active MQ ueber Namensdienst");
		ConnectionFactory connectionFactory = 
				(ConnectionFactory) 
				ctx.lookup("ConnectionFactory");
		
		System.out.println("[INFO] Erstelle Verbindung zum Active MQ");
		connection = connectionFactory.createConnection();
		connection.start();
		
		System.out.println("[INFO] Erzeuge Sitzung");
		session = connection.
				createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic updateTopic = 
				session.createTopic(NEWS_TOPIC_NAME);
		updateProducer = session.createProducer(updateTopic);

		connection.start();
	}

	public void publish(String state) throws JMSException {
		TextMessage message = session.createTextMessage(state);
		updateProducer.send(message);
	}

	public void release() throws JMSException {
		if (connection != null) {
			connection.stop();
			connection.close();
		}
	}
	
    public static void main(String[] args)
    {
    	try {
			NewsServer server = NewsServer.newNewsServer();
			server.publish("VV Vorlesung ist super");
			Thread.sleep(5000);
			server.publish("Messaging macht spass");
			Thread.sleep(5000);
			server.publish("Remoting ist auch nett");			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}

