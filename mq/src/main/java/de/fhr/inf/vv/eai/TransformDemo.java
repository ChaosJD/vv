package de.fhr.inf.vv.eai;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class TransformDemo {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			public void configure() throws Exception {
				from("file://data/inbox?noop=true")
				.to("xslt:file:data/test.xsl")
				.to("file://data/outbox");
			}
		});
		context.start();
		Thread.currentThread().sleep(10000);
		context.stop();
	}

}
