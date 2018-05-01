package de.fhr.inf.vv.threads.queue;

import de.fhr.inf.vv.threads.SlowOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class AsyncLoggerWithQueue implements ILogger {
	private OutputStream file = null;
	private BlockingQueue<String> messages = new LinkedBlockingQueue<String>(10);
	private Thread worker = null;

	public AsyncLoggerWithQueue(String logFile) {
		try {
			file = new SlowOutputStream( new FileOutputStream(logFile));

			Runnable logTask = new Runnable() {
				public void run() {
					while (true) { // Endlosschleife
						try {
  						    String message = messages.take(); // Consumer, blockiert
						    if (message.startsWith("shutdown"))
							   break;
						    file.write(message.getBytes());
						    file.write('\n');
						} 
						catch (Exception x) {
							x.printStackTrace();
							break;
						}
					}
				}
			};
			
			worker = new Thread(logTask);
			worker.start();
		} catch (IOException ex) {
			throw new RuntimeException(ex); // Chaining
		}
	}

	public void close() {
		try {
			worker.join(); // Warten auf den Worker!
		} catch (Exception ex) {
			throw new RuntimeException(ex); // Chaining
		}
	}

	public void logLine(final String toLog) {
		try {
			messages.put(toLog);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
