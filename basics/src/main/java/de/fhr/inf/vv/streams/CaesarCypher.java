package de.fhr.inf.vv.streams;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Demonstration des Decorator Pattners
 * Hier mit einem FilterStream = Decorator auf einen OutputStream
 * Implementiert wird hier die Caesar Verschluesselung
 * @author be
 *
 */
public class CaesarCypher extends FilterOutputStream {
	/** 
	 * Verschiebung bei Caesar
	 */
	private int shift = 0;
	
	/**
	 * Decorator fuer OutputStream
	 * @param decorated Ausgabestrom, der Dekoriert werden soll
	 * @see FilterOutputStream
	 */
	public CaesarCypher(OutputStream decorated, int shift) {
		super(decorated);
		this.shift = shift;
	}

	/**
	 * write (Standardmethode vom OutputStream) wird ueberschrieben
	 * @param c Zeichen im Strom
	 * @throws IOException Fehlerbehandlung erfolgt extern
	 * @see OutputStream.write
	 */
	public void write(int c) throws IOException {
		if (c >= 'a' && c <= 'z') {
			c = (c-'a' + shift) % 26 + 'a';			
		}
		else if (c >= 'A' && c <= 'Z') {
			c = (c-'A' + shift) % 26 + 'A';						
		}
		
		super.write(c); 
	}

	/**
	 * Testtreiber fuer den Caesar Cypher
	 * @param args Kommandozeilenparameter werden ignoriert
	 * @throws IOException Fehlerbehandlung wurde hier gespart
	 */
	public static void main(String[] args) throws IOException {
		OutputStream ausgabe = new CaesarCypher(System.out, 5);
		String x = "Mein Name ist ALFRED";
		PrintStream ps = new PrintStream(ausgabe);
		ps.println(x);

		ausgabe.flush(); // Puffer leeren
		ausgabe.close();
	}
}
