package de.fhr.inf.vv.threads.result;

public class Application {

	public static void main(String[] args) throws Exception {
    	Thread.setDefaultUncaughtExceptionHandler(new 
    			Thread.UncaughtExceptionHandler() {
    		
    		public void uncaughtException(Thread t, Throwable e) {
    			System.out.println("Exception in run " + t 
    					+ " Exception" + e.getMessage());
    			e.printStackTrace();
    		}
    	});
    	
    	LoggerAcknowledge logger = new LoggerAcknowledge("logfile.txt");
		
		// Polling
		int versuch = 1;
		while(true) {
		    String result = logger.poll();
		    if (result != null) {
				System.out.println("Erfolg " + result);
				break;
			} 
			System.out.println("   Versuch Nr." + versuch++);
			Thread.sleep(100);
		}
		
		// Callback
		logger.logLine("Zweite Zeile mit Callback",
				new LoggerAcknowledge.AcknowledgeListener() {
			
			public void notify(String ack) {
				System.out.println("    Erfolg " + ack);
			}
		});
		
		
		// Synchron
		logger.logLine("Dritte Zeile Synchron");
		System.out.println("System does something else");
	    String result = logger.synchronous();
	    System.out.println("Erfolg " + result);
		
	    logger.close();
		
	}

}
