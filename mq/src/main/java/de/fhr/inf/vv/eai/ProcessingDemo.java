package de.fhr.inf.vv.eai;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProcessingDemo {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			public void configure() throws Exception {

				from("file://data/inbox?noop=true").
				process(new Processor() {
					public void process(Exchange exc) throws Exception {
						System.out.println("Processing: "
								+ exc.getIn().getHeader("CamelFileName"));
						System.out.println("Content: "
								+ exc.getIn().getBody(String.class));
					}
				});
				
			}

		});
		context.start();
		Thread.currentThread().sleep(10000);
		context.stop();
	}

}
