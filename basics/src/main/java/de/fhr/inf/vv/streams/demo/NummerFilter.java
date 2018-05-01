package de.fhr.inf.vv.streams.demo;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Demonstration des Decorator Pattners
 * Hier mit einem FilterStream = Decorator auf einen OutputStream
 * @author be
 *
 */
public class NummerFilter extends FilterOutputStream {
	
	/**
	 * Decorator fuer OutputStream
	 * @param decorated Ausgabestrom, der Dekoriert werden soll
	 * @see FilterOutputStream
	 */
	public NummerFilter(OutputStream decorated) {
		super(decorated); 
	}

	/**
	 * write (Standardmethode vom OutputStream) wird ueberschrieben
	 * @param c Zeichen im Strom
	 * @throws IOException Fehlerbehandlung erfolgt extern
	 * @see OutputStream.write
	 */
	public void write(int c) throws IOException {
		switch (c) {
		case '1': super.write("eins ".getBytes());break;
		case '2': super.write("zwei ".getBytes());break;
		case '3': super.write("drei ".getBytes());break;
		case '4': super.write("vier ".getBytes());break;
		case '5': super.write("fuenf ".getBytes());break;
		case '6': super.write("sechs ".getBytes());break;
		case '7': super.write("sieben ".getBytes());break;
		case '8': super.write("acht ".getBytes());break;
		case '9': super.write("neun ".getBytes());break;
		case '0': super.write("null ".getBytes());break;

		default: super.write(c);
		} 
	}

	/**
	 * Testtreiber fuer den Nummernfilter, besser waere ein
	 * JUnit test
	 * @param args Kommandozeilenparameter werden ignoriert
	 * @throws IOException Fehlerbehandlung wurde hier gespart
	 */
	public static void main(String[] args) throws IOException {
		OutputStream ausgabe = new NummerFilter(System.out);
		String x = "Meine Telefonnummer ist: 089/1234567-890";
		PrintStream ps = new PrintStream(ausgabe);
		ps.println(x);
		ausgabe.flush(); // Puffer leeren
		ausgabe.close();
	}
}
