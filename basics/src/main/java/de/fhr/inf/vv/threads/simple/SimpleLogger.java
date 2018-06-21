package de.fhr.inf.vv.threads.simple;

import de.fhr.inf.vv.threads.SlowOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SimpleLogger implements ILogger {
    OutputStream logFileStream = null;
    
    public SimpleLogger(String fileName) {
    	try {
			logFileStream = new SlowOutputStream( new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
	
	public void logLine(final String line) {
		Runnable task = new Runnable() {
			public void run() {
				try {
					for (byte b: line.getBytes()) {
					   logFileStream.write(b);
					}
					logFileStream.flush();
					System.out.println("Log:" + line);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};	
		};
		
		
		Thread worker = new Thread(task);
		worker.start();
	}

	public void close() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleLogger sp = new SimpleLogger("log.txt");
		
		sp.logLine("Erste Nachricht");
		sp.logLine("Zweite Nachricht");
		sp.logLine("Dritte Nachricht");
		
		System.out.println("Ende !!");
	}

}
