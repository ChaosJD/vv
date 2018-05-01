package de.fhr.inf.vv.streams;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * ConsoleLogger liest von der Konsole eine Zeile ein und
 * Schreibt diese dann in eine Datei.
 * @author Gerd Beneken
 */
public class ConsoleLogger {
	private static final String LOG_FILE = "console.txt";
	private static final String EXIT = "EXIT";

	/** 
	 * Achtung Programm ist kein Produktiver Code
	 * Es dient zur Demonstration, wie man Streams "zufuss" programmiert
	 * @param args Kommandozeilen Parameter
	 */
	public static void main(String[] args)  {

		InputStream in = null; 		// Daten byteweise lesen

		try (OutputStream out = new FileOutputStream(LOG_FILE); ) {
			in  = System.in; // Daten von Konsole Lesen
             
			// Hier wird eine Datei goeffnet

            Scanner scan = new Scanner(in);

			// Wir programmieren hier ein reaktives System!
			// Daher hier eine Endlosschleife
			// Endlosschleife kann unterbrochen werden
			while (true) { 
				String zeile = scan.nextLine();
				if (zeile.startsWith(EXIT)) break;	
				out.write(zeile.getBytes());
				out.write('\n'); // Unix
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			// Fehlerbehandlung fehlt
		} 
	}
}
