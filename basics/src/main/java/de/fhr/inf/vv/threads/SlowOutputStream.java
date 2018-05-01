package de.fhr.inf.vv.threads;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SlowOutputStream extends FilterOutputStream {
	public SlowOutputStream(OutputStream out) {
		super(out);
	}

	public void write(int b) throws IOException {
		this.waitABit();
		super.write(b);
	}

	public void write(byte[] b) throws IOException {
		this.waitABit();
		super.write(b);
	}

	public void write(byte[] b, int off, int len) throws IOException {
		this.waitABit();
		super.write(b, off, len);
	}

    private void waitABit(){
    	try {
    		Thread.currentThread().sleep(200);
    	}
    	catch(InterruptedException ignore) {}    	
    }
}
