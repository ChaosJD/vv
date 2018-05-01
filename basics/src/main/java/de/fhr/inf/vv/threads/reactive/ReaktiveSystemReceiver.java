package de.fhr.inf.vv.threads.reactive;

import java.util.Queue;

public class ReaktiveSystemReceiver extends Thread {
	private Queue<String> inputQueue;
	private Queue<String> outputQueue;
	
	
	public ReaktiveSystemReceiver(Queue<String> inputQueue, Queue<String> outputQueue) {
		this.inputQueue = inputQueue;
		this.outputQueue = outputQueue;
	}
	
	/**
	 * Run simuliert eine Verarbeitung, die etwas länger dauert
	 */
	public void run() {
		try {
			while(true) {
			     String request = inputQueue.poll();
			     if (request != null)  // Nachricht erhalten
			     {
			    	 String response = "Verarbeitet:" + request;
			    	 this.sleep(5000); // Simulation, dass Bearbeitung viel Zeit kostet
			    	 outputQueue.offer(response);
			     }
			     else {
			    	 this.sleep(100); // CPU schonen
			     }
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
