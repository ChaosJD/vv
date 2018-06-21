package de.fhr.inf.vv.mq.hello;

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

public class HelloWorldReceiverAsynchron {
	public static void main(String[] args) {
        // Properties fuer JNDI zum Auffinden des ActiveMQ Servers
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");

		try {
			Context ctx = new InitialContext(props);	
			ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("ConnectionFactory");

			Connection connection = connectionFactory.createConnection();
			connection.setExceptionListener(new ExceptionListener(){
				public void onException(JMSException e) {
					System.out.println("JMS Exception occured.  Shutting down client.");
					e.printStackTrace();
			}});
			
			connection.start();

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			Queue destination = (Queue) ctx.lookup("dynamicQueues/vvqueue");
			// = session.createQueue("vvqueue");

			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(
					new MessageListener(){
						public void onMessage(Message message) {
							try {
								TextMessage text = (TextMessage)message;
								System.out.println("Received:" + text.getText());
							} catch (Exception e) {
								e.printStackTrace();
							}				
			}});

			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("Closing connection");
					consumer.close();
					session.close();
					connection.close();				
				}
			}

		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
		
	}
}
