package de.fhr.inf.vv.threads.reactive;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

public class ReactiveSystemSender {
	private Queue<String> inputQueue;
	private Queue<String> outputQueue;

	public ReactiveSystemSender(Queue<String> inputQueue, Queue<String> outputQueue) {
		this.inputQueue = inputQueue;
		this.outputQueue = outputQueue;
	}
	    
    public static String readLine(InputStream incoming) throws IOException {
    	StringBuffer result = new StringBuffer();
    	int c = 0;
    	while ((c=incoming.read()) > -1) {
    		if (c == '\r') continue;
    		result.append((char) c);
    		if (c == '\n') break;
    	}
    	return result.toString();
    }
    
    public String sendMessageSynchronous(String message) throws Exception {
    	outputQueue.offer(message);
    	while (inputQueue.peek() == null) {
				Thread.currentThread().sleep(100);
    	}
    	return inputQueue.poll();
    }

    public void sendMessageAsynchronous(String message) {
    	outputQueue.offer(message);
    }

    public String pollMessage() throws Exception {
    	return inputQueue.poll();
    }
    
    
    
	public static void main(String[] args) throws Exception {
		Queue<String> senderToReceiver = new LinkedList<String>();
		Queue<String> receiverToSender = new LinkedList<String>();
		
		ReactiveSystemSender sender = 
				new ReactiveSystemSender(receiverToSender, senderToReceiver);
		ReaktiveSystemReceiver receiver = 
				new ReaktiveSystemReceiver(senderToReceiver, receiverToSender);

		receiver.start(); // Hier wird der Thread gestartet 
		// AB hier zwei Threads: Main und der gerade gestartete Thread
		
		while (true) {
			// 1. Auf Ereignis warten
			System.out.print("$:");
			String request = readLine(System.in); // Zeile von der Konsole Einlesen
            System.out.println("--> " + request );
			// Ereignis verarbieten
			if (request.startsWith("shutdown")) break;
			
			// 1. Synchroner Aufruf des Anderen Threads
            // String response = sender.sendMessageSynchronous(request);
            // System.out.println("<-- '" + response + "'");
			
			// 2. Asynchroner Aufruf des anderen Threads
			//    und Polling, ob schon ein Ergebnis da ist
			sender.sendMessageAsynchronous(request);
			String response = null;
			while ((response = sender.pollMessage()) != null) {
	            System.out.println("<-- " + response);				
			}
			
			
		}
		
		System.out.println("System terminated");
		
	}

}
