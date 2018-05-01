package de.fhr.inf.vv.threads.reactive;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReactiveSystemBlocking {
	// Event = String, der eine Zeile darstellt
    private InputStream incoming;
    private OutputStream outgoing;
    
    public ReactiveSystemBlocking(InputStream incoming, OutputStream outgoing) {
    	this.incoming = incoming;
    	this.outgoing = outgoing;
    }

    public String receive() throws IOException {
    	StringBuffer result = new StringBuffer();
    	int c = 0;
    	while ((c=incoming.read()) > -1) {
    		if (c == '\r') continue;
    		result.append((char) c);
    		if (c == '\n') break;
    	}
    	return result.toString();
    }
    
    public void send(String event) throws IOException {
    	byte[] toSend = event.getBytes();
    	outgoing.write(toSend);
    	outgoing.flush();
    }
    
	
	public static void main(String[] args) throws IOException {
		ReactiveSystemBlocking echo = new ReactiveSystemBlocking(System.in, System.out);
		
		while (true) {
			// 1. Auf Ereignis warten
			String event = echo.receive(); // Blockierend
			// Ereignis verarbieten
			if (event.startsWith("shutdown")) break;
			// Ergebnisse weitergeben
			echo.send("Echo: " + event);
		}
		
		System.out.println("System terminated");
		
	}

}
