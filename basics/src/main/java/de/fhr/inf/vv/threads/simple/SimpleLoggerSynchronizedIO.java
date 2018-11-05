package de.fhr.inf.vv.threads.simple;

import de.fhr.inf.vv.threads.SlowOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SimpleLoggerSynchronizedIO implements ILogger {
    private OutputStream logFileStream;
    private Thread worker = null;
	
	public SimpleLoggerSynchronizedIO(String logFileName) {
		try {
		    logFileStream = new SlowOutputStream(
		    		new FileOutputStream(logFileName));
		}
		catch(FileNotFoundException ex) {
			try {logFileStream.close();} catch (Exception ignore){ignore.printStackTrace();};
			throw new RuntimeException(ex);
		}
	}
	
	public void logLine(final String line) {
		try {
		   if (worker != null ) {
			   worker.join();  // Warten, bis der vorhergehende Thread fertig ist
		   }
		   
		   Runnable task = new Runnable() {
			public void run() {
				try {
					for (byte b: line.getBytes()) {
						logFileStream.write(b);
					}
				    logFileStream.write('\n');
				    logFileStream.flush();
				    System.out.println("Logged:" + line);
				}
				catch(IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		   };
		   
		   worker = new Thread(task);
		   worker.start(); // Asynchron ausfuehren
		}
		catch(InterruptedException ex) {
			throw new RuntimeException(ex);			
		}

	}

	public void close() {
		try {
			if (worker != null) worker.join();
		    logFileStream.close();
		}
		catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
