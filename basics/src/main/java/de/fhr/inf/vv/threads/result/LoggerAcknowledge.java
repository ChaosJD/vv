package de.fhr.inf.vv.threads.result;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import de.fhr.inf.vv.ueb05.SlowOutputStream;

public class LoggerAcknowledge implements ILogger {
	public interface AcknowledgeListener {
		void notify(String ack);
	}
	
    private OutputStream file = null;
    private FutureTask<String> task = null;
    private Thread worker = null;
	
    public LoggerAcknowledge(String logFile) {
		try {
    	    file = new SlowOutputStream(
    	    		new FileOutputStream(logFile));
		}
		catch (IOException ex) {
			try {file.close();} catch (Exception ignore){};
		    throw new RuntimeException(ex); // Chaining
		}
    }
    
	public void close() {
		try {
			worker.join(); // Warten auf den Worker!
		    file.close();
			file = null;
		}
		catch (Exception ex) {
		    throw new RuntimeException(ex); // Chaining
		}	
	}

    public void logLine(final String line) {
    	if (worker != null) try { worker.join(); } catch (Exception e) {throw new RuntimeException(e);}
    	
		Callable<String> callable = new Callable<String>() {
			public String call() throws Exception {
				file.write(line.getBytes());
				file.write('\n');
				file.flush();
				System.out.println("Log:" + line);
				return "ACK";
			}
		};

		task = new FutureTask<String>(callable);
		worker = new Thread(task);
		worker.start();
    }
    
    public void logLine(final String line, final AcknowledgeListener callback) {
    	if (worker != null) try { worker.join(); } catch (Exception e) {throw new RuntimeException(e);}
    	
		Runnable task = new Runnable() {
			public void run() {
				try {
					file.write(line.getBytes());
					file.write('\n');
					file.flush();
					System.out.println("Log:" + line);
					callback.notify("ACK");
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		};
		worker = new Thread(task);
		worker.start();
    }
    
    public String poll() {
    	if (! task.isDone()) return null;

    	String result = null;
			try {
				result = task.get(10, TimeUnit.MILLISECONDS);
			} 
			catch (TimeoutException ignore) { } // TimeOut ingorieren (poll)
			catch (Exception e) {
				throw new RuntimeException(e);				
			}
    	return result;
    }

    public String synchronous() {

    	String result = null;
    	try {
			result = task.get();
		} catch (Exception e) {
			throw new RuntimeException(e);
	    }
    	return result;
    }
}
