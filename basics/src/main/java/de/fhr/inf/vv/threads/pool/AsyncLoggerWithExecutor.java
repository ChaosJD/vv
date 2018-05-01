package de.fhr.inf.vv.threads.pool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.fhr.inf.vv.threads.SlowOutputStream;

public class AsyncLoggerWithExecutor implements ILogger {
    private OutputStream file = null;
	private final ExecutorService exec = 
			Executors.newSingleThreadExecutor();

	public AsyncLoggerWithExecutor(String logFile) {
		try {
    	    file = new SlowOutputStream(new FileOutputStream(logFile));
		}
		catch (IOException ex) {
			try {file.close();} catch (Exception ignore){};
		    throw new RuntimeException(ex); // Chaining
		}
	}
	
	
	public void close() {
		exec.shutdown();
	}
	
	public void logLine(final String toLog) {
		Runnable logTask = new Runnable() {
			public void run() {
				try {
					file.write(toLog.getBytes());
					file.flush();
				    System.out.println("Logged:" + toLog);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}	
		};
		
		if (! exec.isShutdown()) exec.execute(logTask);
		else throw new RuntimeException("Shutdown-Exception");
	}	
}
