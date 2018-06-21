package de.fhr.inf.vv.eai;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class RoutingDemo {

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
//				from("file://data/inbox?noop=true").process(new Processor() {
//					public void process(Exchange arg0) throws Exception {
//						System.out.println("processing "
//								+ arg0.getIn().getHeader("CamelFileName"));
//					}
//				})
//				.to("jms:queue:incoming").process(new Processor() {
//					public void process(Exchange arg0) throws Exception {
//						System.out.println("Queue "
//								+ arg0.getIn().getHeader("CamelFileName"));
//					}
//				});
//				
				from("jms:queue:incoming")
				.choice()
				.when(header("CamelFileName").
						endsWith(".xml")).
				        to("jms:queue:inxml")
				.when(header("CamelFileName").
						endsWith(".txt")).
				        to("jms:queue:intxt")
				.otherwise().to("jms:queue:default");

//				from("jms:queue:inxml").filter(xpath("/Kunde[@test='true']")).process(new Processor() {
//					public void process(Exchange exc) throws Exception {
//						System.out.println("XML-Test:"
//								+ exc.getIn().getHeader("CamelFileName"));
//					}
//				})
//				.to("jms:queue:processed");
//
//				from("jms:queue:intxt").process(new Processor() {
//					public void process(Exchange exc) throws Exception {
//						System.out.println("TXT:"
//								+ exc.getIn().getHeader("CamelFileName"));
//					}
//				})
//				.to("jms:queue:processed");

			}

		});
		context.start();
		Thread.currentThread().sleep(10000);
		context.stop();
	}

}
