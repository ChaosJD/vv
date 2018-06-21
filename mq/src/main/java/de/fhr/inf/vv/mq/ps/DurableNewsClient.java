package de.fhr.inf.vv.mq.ps;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.*;

public class DurableNewsClient implements MessageListener {
	public static final String NEWS_TOPIC_NAME = "NEWS_FEED";
	private Connection connection;
	private MessageConsumer updateConsumer;
    private JLabel label;
	private JFrame frame;
	
	private DurableNewsClient() {
		super();
	}

	public static DurableNewsClient newNewsClient() throws JMSException {
		DurableNewsClient newsClient = new DurableNewsClient();
		return newsClient;
	}

	private void initialize() throws Exception {

		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");

		Context ctx = new InitialContext(props);	
		ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("ConnectionFactory");

		connection = connectionFactory.createConnection();
        // Client identifizierbar machen
        connection.setClientID("Schububu");

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic updateTopic = (Topic)ctx.
				lookup("dynamicTopics/"+NEWS_TOPIC_NAME);
        // Hier wird der Durable Subscriber erzeugt
		
        updateConsumer = session.createDurableSubscriber(
        		updateTopic,"Schububu");
		updateConsumer.setMessageListener(this);
	}

	public void onMessage(Message message) {
		try {
			TextMessage textMsg = (TextMessage) message; // assume cast always works
			String news = textMsg.getText();

			StringBuffer buf = new StringBuffer();
	        buf.append("<HTML><CENTER><h1>");
	        buf.append(news);
	        buf.append("</h1></CENTER></HTML>");

			label.setText(buf.toString());
			frame.pack();
			
			Thread.sleep(2000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void connect() throws JMSException {
		connection.start();
	}

	public void disconnect() throws JMSException {
		if (connection != null) {
			connection.stop();
			connection.close();
		}
	}

    private void createAndShowGUI() {
        frame = new JFrame("Rosenheimer Newsfeed");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				try {
					disconnect();
				} 
				catch (JMSException ex) { ex.printStackTrace();}
				System.exit(0);
			}
		});

        label = new JLabel("<< Hier die Neuigkeiten >>");
        JLabel titel = new JLabel("Aktuell:");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.getContentPane().add(titel, BorderLayout.WEST);


        // Stoesst Layouting an
        frame.pack();
        // Sichtbar machen
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {

    	final DurableNewsClient newsClient = newNewsClient();

    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                newsClient.createAndShowGUI();
            }
        });
        
		newsClient.initialize();
    	newsClient.connect();
    
    }
}
