package de.fhr.inf.vv.expb.hello;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class HelloWorldReceiver implements MessageListener, ExceptionListener {
	public static void main(String[] args) {
		Session session = null;
		Connection connection = null;
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");

		
		try {
			System.out.println("[INFO] Erzeuge den Context, zum Zugriff auf den Namensdienst");
			Context ctx = new InitialContext(props);	
			System.out.println("[INFO] Beschaffe Zugriffsmoeglichkeit auf Active MQ ueber Namensdienst");
			ConnectionFactory connectionFactory = 
					(ConnectionFactory) 
					ctx.lookup("ConnectionFactory");
			
			System.out.println("[INFO] Erstelle Verbindung zum Active MQ");
			connection = connectionFactory.createConnection();
			// connection.setExceptionListener(new HelloWorldReceiver());
			connection.start();
			
			System.out.println("[INFO] Erzeuge Sitzung");

			session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			System.out.println("[INFO] Greife auf VVQUEUE ueber Namensdienst zu");			
			Queue destination = (Queue) 
					ctx.lookup("dynamicQueues/vvqueue");
			// = session.createQueue("vvqueue");
			

			System.out.println("[INFO] Lese Nachrichten");
			MessageConsumer consumer = session.createConsumer(
					destination);

			for (int i=0; i<60; i++ ) {
    			TextMessage message = 
					(TextMessage)consumer.receive();
			    System.out.println("[INFO] Received:" + message.getText());
			}
			
			System.out.println("[INFO] Closing connection");

			consumer.close();
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

	public void onException(JMSException e) {
		System.out.println("JMS Exception occured.  Shutting down client.");
		e.printStackTrace();
	}

	public void onMessage(Message message) {
		System.out.println("Message Received:" + message);
	}
}
