package de.fhr.inf.vv.eai;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FileToJMS {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		// JMS + ActiveMQ Initialisierung
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

		Context naming = new InitialContext(props);
		ConnectionFactory connectionFactory = (ConnectionFactory) naming
				.lookup("ConnectionFactory");

		CamelContext context = new DefaultCamelContext();
		context.addComponent("jms",
				JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.addRoutes(new RouteBuilder() {

			public void configure() throws Exception {

				from("file://data/inbox")
				.process(new Processor() {
					public void process(Exchange exc) 
							throws Exception {
						System.out.println("Processing: "
				+ exc.getIn().getHeader("CamelFileName"));
					}
				})
				.to("jms:queue:incoming").process(new Processor() {
					public void process(Exchange exc) 
					throws Exception {
						System.out.println("Queued:"
				+ exc.getIn().getHeader("CamelFileName"));
					}
				});
				
			}

		});
		context.start();
		Thread.currentThread().sleep(10000);
		context.stop();
	}

}
