package de.fhr.inf.vv.threads.simple;

public class Application {

	public static void main(String[] args) {
    	Thread.setDefaultUncaughtExceptionHandler(new 
    			Thread.UncaughtExceptionHandler() {
    		
    		public void uncaughtException(Thread t, Throwable e) {
    			System.out.println("Exception in run " + t 
    					+ " Exception" + e.getMessage());
    			e.printStackTrace();
    		}
    	});
    	
		ILogger logger = new SimpleLoggerSynchronizedIO("logfile.txt");
		
		logger.logLine("Ester Kommentar");
		logger.logLine("Zweiter Kommentar");
		logger.logLine("Dritter Kommentar");
		System.out.println("Application does something else");
		
		logger.close();
	}

}
