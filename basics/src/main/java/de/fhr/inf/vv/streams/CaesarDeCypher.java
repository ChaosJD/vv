package de.fhr.inf.vv.streams;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Demonstration des Decorator Pattners
 * Hier mit einem FilterStream = Decorator auf einen InputStream
 * Implementiert wird hier die Caesar Entschluesselung
 * @author be
 */
public class CaesarDeCypher extends FilterInputStream {
	private int shift = 0;
	/**
	 * Decorator f�r InputStream
	 * @param decorated Ausgabestrom, der Dekoriert werden soll
	 * @param shift Versatz beim Chiffre
	 * @see FilterInputStream
	 */
	public CaesarDeCypher(InputStream decorated, int shift) {
		super(decorated);
		this.shift = shift;
	}

	/**
	 * Implementiert Caesar Enschluesselung
	 * @returns dechiffriertes Zeichen aus Eingabestrom
	 * @throws IOException Fehlerbehandlung erfolgt extern
	 * @see InputStream.read
	 */
	@Override
	public int read() throws IOException {
		int c = super.read();
		if (c >= 'a' && c <= 'z') {
			c = (c- 'a' - shift + 26) % 26 + 'a';			
		}
		else if (c >= 'A' && c <= 'Z') {
			c = (c - 'A' - shift + 26) % 26 + 'A';						
		}

		return c;
	}

	/**
	 * Testtreiber fuer den Nummernfilter
	 * @param args Kommandozeilenparameter werden ignoriert
	 * @throws IOException Fehlerbehandlung wurde hier gespart
	 */
	public static void main(String[] args) throws IOException {
		String x = "Rjns Sfrj nxy FQKWJI";
		ByteArrayInputStream byteInput = new ByteArrayInputStream(x.getBytes());

		InputStream eingabe = new CaesarDeCypher(byteInput, 5);
		
		int c=0;
		while ((c= eingabe.read()) > -1 ) {
			System.out.print((char)c);
		}
		System.out.println();
		
		eingabe.close();
		
	}
}
