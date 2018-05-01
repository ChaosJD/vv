package de.fhr.inf.vv.streams.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Demonstration, wie Zeichenweise geschrieben und gelesen wird.
 * Diesesmal aber gepuffert (so sollte man das machen)
 * Hier werden Daten von einer Datei in eine andere kopiert
 * @author be
 *
 */
public class CopyBinaryFileBuffered {
	private static final String LOG_FILE = "console.txt";
	private static final String DESTINATION = "copyOfConsole.txt";
	private static final int EOF = -1; // End Of File

	/** 
	 * Achtung Programm ist kein Produktiver Code
	 * Es wird Zeichenweise gelesen und geschrieben
	 * Normalerweise wuerde man gepuffert lesen und schreiben
	 * 
	 * @param args Kommandozeilenparameter
	 */
	public static void main(String[] args) {

		File sourceFile = new File(LOG_FILE);
		File destinationFile = new File(DESTINATION);

// Ab Java 7 alternativ, Achtung auch finally Block auskommentieren!!
        try (InputStream in = new FileInputStream(sourceFile);
			   OutputStream out = new FileOutputStream(destinationFile)) {

			int zeichen = 0;
			while ((zeichen = in.read()) != EOF) {
				out.write(zeichen);
			}
		} 

		// Fehlerbehandlung
		catch(FileNotFoundException notfound) {
			// Wenn File Not Found gesondert zu behandeln
			System.err.println("[Error] " + notfound.getMessage());
		}
		catch(IOException ioe) {
			// Behandlung der restlichen Probleme
			System.err.println("[Error] " + ioe.getMessage());
			
		}
	}
}
