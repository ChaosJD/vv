package de.fhr.inf.vv.mq.hello;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class HelloWorldSender {
	public static void main(String[] args) {
		Connection connection=null;
		Session session=null;
		
        // Properties fuer JNDI zum Auffinden des ActiveMQ Servers
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.activemq.jndi." +
				"ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL,
				"tcp://localhost:61616");
		
		try {
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
			session = 
				connection.createSession(false, 
						Session.AUTO_ACKNOWLEDGE);
			
			System.out.println("[INFO] Greife auf VVQUEUE ueber Namensdienst zu");
			Queue destination = (Queue) 
					ctx.lookup("dynamicQueues/vvqueue");
			// = session.createQueue("vvqueue");
			
			System.out.println("[INFO] Erzeuge Nachrichten");
			MessageProducer producer = session.createProducer(
					destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			TextMessage message = session.
			    createTextMessage("Ich bin eine Demo-Nachricht");

			producer.send(message);
			
			for (int i=0; i< 100; i++) {
				TextMessage message2 = session.createTextMessage("Schubidu " + i);
			    producer.send(message2);				
			}

			System.out.println("[INFO] Nachrichten sind versendet!");
			
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		} finally {
			try {
				session.close();
			}
			catch(Throwable ignore){}
			try { 
				connection.stop();
				connection.close();
			} catch (Throwable ignore) {
			}
		}
	}

}
